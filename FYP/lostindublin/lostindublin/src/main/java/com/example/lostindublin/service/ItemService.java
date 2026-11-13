package com.example.lostindublin.service;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.lostindublin.dao.ItemDAO;
import com.example.lostindublin.dao.UserDAO;
import com.example.lostindublin.model.Item;
import com.example.lostindublin.model.User;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@RestController
@RequestMapping("/ItemService")
public class ItemService {

	public ItemService() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping("/create")
	public RedirectView createItem(@RequestParam("ownerId") int ownerId, @RequestParam("itemName") String itemName,
			@RequestParam("itemDescription") String itemDescription, @RequestParam("itemStatus") String itemStatus,
			@RequestParam("itemRegisteredAt") String itemRegisteredAt) throws URISyntaxException {

		// URI uriFound = new URI("http://localhost:8080/FYP/home.jsp");
		// URI uriNotFound = new URI("http://localhost:8080/FYP/items.jsp");

		UserDAO userDao = new UserDAO();
		User owner = userDao.getUserById(ownerId);

		if (owner == null) {
			return new RedirectView("/items.jsp");
		}

		String uniqueQrCodeText = UUID.randomUUID().toString();

		ItemDAO itemDao = new ItemDAO();

		Item item = new Item(owner, itemName, itemDescription, uniqueQrCodeText, itemStatus, itemRegisteredAt);

		itemDao.persist(item);

		return new RedirectView("/items.jsp");

	}

	@GetMapping("/item/{itemId}")
	public Item getItemById(@PathVariable int itemId) {
		ItemDAO dao = new ItemDAO();

		return dao.getItemById(itemId);
	}

	@GetMapping("/ownerItems")
	public List<Item> getItemsByOwnerForm(@RequestParam("userId") int userId) {
		ItemDAO dao = new ItemDAO();

		return dao.getItemsByOwner(userId);
	}
	
	
	@GetMapping("/viewItemQRCodeForm")
	public RedirectView viewQRCodeForm(@RequestParam("itemId") int itemId) {
		ItemDAO dao = new ItemDAO();
		Item item = dao.getItemById(itemId);
		
		if (item == null) {
			return new RedirectView("/items.jsp");
		}
		
		return new RedirectView("/qrcode/image" + itemId);
	}
	

	@GetMapping(value = "/qrcode/image", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] getQRCodeImage(@RequestParam("itemId") int itemId) throws Exception {
	    ItemDAO dao = new ItemDAO();
	    Item item = dao.getItemById(itemId);

	    QRCodeWriter qrCodeWriter = new QRCodeWriter();
	    BitMatrix bitMatrix = qrCodeWriter.encode(item.getItemQrCode(), BarcodeFormat.QR_CODE, 250, 250);

	    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
	    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
	    return pngOutputStream.toByteArray();
	}
	
	

	@PostMapping("/update")
	public String updateItem(Item item) {
		ItemDAO dao = new ItemDAO();
		dao.merge(item);

		return item.getItemName().toString() + "updated";
	}
	
	
	
	

	@GetMapping("/delete/{itemId}")
	public String deleteItem(@PathVariable int itemId) {
		ItemDAO dao = new ItemDAO();
		Item item = dao.getItemById(itemId);

		if (item == null) {
			return "item not found";
		}
		dao.remove(item);

		return "item deleted";
	}

}
