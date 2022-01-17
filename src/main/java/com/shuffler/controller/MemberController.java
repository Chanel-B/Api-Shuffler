package com.shuffler.controller;

import com.shuffler.model.Member;
import com.shuffler.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class MemberController {

    @Autowired
    private MemberRepository userRepository;

    @GetMapping
    public List<Member> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{user_id}")
    public Optional<Member> getUserById(@PathVariable("user_id") int id) {
        return userRepository.findById(id);
    }

    @PostMapping
    public String saveUserEntity(@RequestBody Member user) {
        userRepository.save(user);
        return "Save User Successfully!";
    }

    @DeleteMapping("/{user_id}")
    public String deleteUser(@PathVariable("user_id") int id) {
        userRepository.deleteById(id);
        return "Deleted Successfully!";
    }

    @PutMapping("/{user_id}")
    public String updateUser(@PathVariable("user_id") int id, @RequestBody Member userInput) {
        Member user = userRepository.getById(id);
        user.setName(userInput.getName());
        userRepository.save(user);
        return "Updated Successfully!";
    }

    /**
     * Business logic
     * 1) check if the list is not empty,
     * 2) Let save everyTime the shuffled button is clicked (up to a max number of 5 times),
     * 3) Possibility of shuffling severally time and choose one of the shuffled,
     * 4) Possibility of displaying name with a delay after every each name,
     *
     * @return
     */
    @GetMapping(value = "/shuffle")
    public Collection<Member> getListOfShuffledUsers() {
        List<Member> users = userRepository.findAll();

        if (users.isEmpty())
            System.out.println("No users Found");
        else {
            Collections.shuffle(users);
            Collections.shuffle(users);
            Collections.shuffle(users);
        }
        return users;
    }

}