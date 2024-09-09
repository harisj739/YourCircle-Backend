package com.example.YourCircle.Controller;

import com.example.YourCircle.DTO.ItemDTO;
import com.example.YourCircle.Domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://circle-weld-iota.vercel.app")
public class ItemController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CircleRepository circleRepository;

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/youritems")
    public List<ItemDTO> findUserItems(@RequestParam ("userId") int userId) {

        User user = userRepository.findById(userId).orElse(null);

        if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR: User not found!");

        List<Item> items = itemRepository.findBySender(userId);
        List<ItemDTO> itemDTO_list = new ArrayList<>();
        for (Item i: items) {
            itemDTO_list.add(new ItemDTO(i.getItemId(), i.getItemName(), i.getItemDesc(), i.getCircle().getCircleId(), i.getSender().getUserId(), i.getReceiver().getUserId(), i.getSendDate(), i.getReceiveDate(), i.getRequested(), i.getItemType(), i.getItemCondition()));
        }
        return itemDTO_list;
    }

    @GetMapping("/youritems")
    public List<ItemDTO> findItemByCircle(@RequestParam ("circleId") int circleId) {

        Circle circle = circleRepository.findById(circleId).orElse(null);

        if(circle == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR: Circle not found!");

        List<Item> items = itemRepository.findByCircle(circleId);
        List<ItemDTO> itemDTO_list = new ArrayList<>();
        for (Item i: items) {
            itemDTO_list.add(new ItemDTO(i.getItemId(), i.getItemName(), i.getItemDesc(), i.getCircle().getCircleId(), i.getSender().getUserId(), i.getReceiver().getUserId(), i.getSendDate(), i.getReceiveDate(), i.getRequested(), i.getItemType(), i.getItemCondition()));
        }
        return itemDTO_list;
    }

    @GetMapping("/youritems")
    public List<ItemDTO> findItemByItemName(@RequestParam ("itemName") String itemName) {

        List<Item> items = itemRepository.findByItemName(itemName);
        List<ItemDTO> itemDTO_list = new ArrayList<>();
        for (Item i: items) {
            itemDTO_list.add(new ItemDTO(i.getItemId(), i.getItemName(), i.getItemDesc(), i.getCircle().getCircleId(), i.getSender().getUserId(), i.getReceiver().getUserId(), i.getSendDate(), i.getReceiveDate(), i.getRequested(), i.getItemType(), i.getItemCondition()));
        }
        return itemDTO_list;
    }

    @GetMapping("/youritems")
    public List<ItemDTO> findItemByItemType(@RequestParam ("itemType") String itemType) {

        List<Item> items = itemRepository.findByItemType(itemType);
        List<ItemDTO> itemDTO_list = new ArrayList<>();
        for (Item i: items) {
            itemDTO_list.add(new ItemDTO(i.getItemId(), i.getItemName(), i.getItemDesc(), i.getCircle().getCircleId(), i.getSender().getUserId(), i.getReceiver().getUserId(), i.getSendDate(), i.getReceiveDate(), i.getRequested(), i.getItemType(), i.getItemCondition()));
        }
        return itemDTO_list;
    }

    @GetMapping("/youritems")
    public List<ItemDTO> findItemByItemCondition(@RequestParam ("itemCondition") String itemCondition) {

        List<Item> items = itemRepository.findByItemCondition(itemCondition);
        List<ItemDTO> itemDTO_list = new ArrayList<>();
        for (Item i: items) {
            itemDTO_list.add(new ItemDTO(i.getItemId(), i.getItemName(), i.getItemDesc(), i.getCircle().getCircleId(), i.getSender().getUserId(), i.getReceiver().getUserId(), i.getSendDate(), i.getReceiveDate(), i.getRequested(), i.getItemType(), i.getItemCondition()));
        }
        return itemDTO_list;
    }

    @GetMapping("/youritems")
    public List<ItemDTO> findItemByRequested(@RequestParam ("requested") int requested) {

        List<Item> items = itemRepository.findByRequested(requested);
        List<ItemDTO> itemDTO_list = new ArrayList<>();
        for (Item i: items) {
            itemDTO_list.add(new ItemDTO(i.getItemId(), i.getItemName(), i.getItemDesc(), i.getCircle().getCircleId(), i.getSender().getUserId(), i.getReceiver().getUserId(), i.getSendDate(), i.getReceiveDate(), i.getRequested(), i.getItemType(), i.getItemCondition()));
        }
        return itemDTO_list;
    }

    @GetMapping("/youritems")
    public List<ItemDTO> findItemByDateRange(@RequestParam ("sendDate") Date sendDate, @RequestParam ("receiveDate") Date receiveDate) {

        List<Item> items = itemRepository.findByDateRange(sendDate, receiveDate);
        List<ItemDTO> itemDTO_list = new ArrayList<>();
        for (Item i: items) {
            itemDTO_list.add(new ItemDTO(i.getItemId(), i.getItemName(), i.getItemDesc(), i.getCircle().getCircleId(), i.getSender().getUserId(), i.getReceiver().getUserId(), i.getSendDate(), i.getReceiveDate(), i.getRequested(), i.getItemType(), i.getItemCondition()));
        }
        return itemDTO_list;
    }

    @PostMapping("/item/add")
    public ItemDTO addItem(@RequestBody ItemDTO itemDTO) {
        User sender = userRepository.findById(itemDTO.senderId()).orElse(null);
        if (sender == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR: User not found!");
        }

        User receiver = userRepository.findById(itemDTO.receiverId()).orElse(null);
        if (receiver == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR: User not found!");
        }

        Circle circle = circleRepository.findById(itemDTO.circleId()).orElse(null);

        Item item = new Item();
        item.setItemName(itemDTO.itemName());
        item.setItemDesc(itemDTO.itemDesc());
        item.setCircle(circle);
        item.setSender(sender);
        item.setReceiver(receiver);
        item.setSendDate(itemDTO.sendDate());
        item.setReceiveDate(itemDTO.receiveDate());
        item.setRequested(itemDTO.requested());
        item.setItemType(itemDTO.itemType());
        item.setItemCondition(itemDTO.itemCondition());

        itemRepository.save(item);
        return new ItemDTO(item.getItemId(), item.getItemName(), item.getItemDesc(), item.getCircle().getCircleId(), item.getSender().getUserId(), item.getReceiver().getUserId(), item.getSendDate(), item.getReceiveDate(), item.getRequested(), item.getItemType(), item.getItemCondition());
    }

    @PutMapping("/item/edit")
    public ItemDTO editItem(@RequestBody ItemDTO itemDTO) {
        Item updateItem = itemRepository.findById(itemDTO.circleId()).orElse(null);

        if (updateItem == null) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR: Item '" +itemDTO.itemName() + "' does not exist!");
        } //if

        updateItem.setItemName(itemDTO.itemName());
        updateItem.setItemDesc(itemDTO.itemDesc());
        updateItem.setItemType(itemDTO.itemType());
        updateItem.setItemCondition(itemDTO.itemCondition());

        itemRepository.save(updateItem);
        return new ItemDTO(updateItem.getItemId(), updateItem.getItemName(), updateItem.getItemDesc(), updateItem.getCircle().getCircleId(), updateItem.getSender().getUserId(), updateItem.getReceiver().getUserId(), updateItem.getSendDate(), updateItem.getReceiveDate(), updateItem.getRequested(), updateItem.getItemType(), updateItem.getItemCondition());
    }

    @DeleteMapping("/item/delete/{itemId}")
    public void  deleteItem(@PathVariable("itemId") int itemId) {
        Item deleteItem = itemRepository.findById(itemId).orElse(null);

        if (deleteItem != null) {
            itemRepository.delete(deleteItem);
        } //if
    }
}