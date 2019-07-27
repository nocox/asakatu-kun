package com.asakatu.controller;

import com.asakatu.OkResponse;
import com.asakatu.entity.User;
import com.asakatu.repository.UserRepository;
import com.asakatu.service.S3BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
public class UserDetailController {

    private final
    UserRepository userRepository;

    public UserDetailController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/user/{id}")
    public OkResponse userDetail(@PathVariable long id) {
        User user = userRepository.findById(id).orElseThrow();

        return okUser(user);
    }

    @PutMapping(path = "/user/edit/display_name")
    public OkResponse editUserDisplayName(@RequestParam("displayName") String displayName){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> findUser = userRepository.findByUsername(authentication.getName());
        User user = findUser.orElseThrow();

        user.setDisplayName(displayName);
        userRepository.save(user);

        return okUser(user);
    }

    @Autowired
    private S3BucketService s3BucketService;

    @PutMapping("/user/edit/image")
    public OkResponse uploadFile(@RequestParam("file") MultipartFile file) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> findUser = userRepository.findByUsername(authentication.getName());
        User user = findUser.orElseThrow();

        String fileName = s3BucketService.storeFile(file);
        user.setImagePath(s3BucketService.getObjectURL(fileName));
        userRepository.save(user);

        return okUser(user);
    }

    private OkResponse okUser(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> findUser = userRepository.findByUsername(authentication.getName());

        UserDetailResponse userDetailResponse = new UserDetailResponse(user);
        findUser.ifPresent(value -> userDetailResponse.setMyself(value.getId().equals(user.getId())));
        return new OkResponse(userDetailResponse);
    }
}


class UserDetailResponse {

    private String imagePath;
    private String displayName;
    private String username;
    private String email;
    private boolean myself;

    void setMyself(boolean myself) {
        this.myself = myself;
    }

    UserDetailResponse(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.displayName = user.getDisplayName();
        this.imagePath = user.getImagePath();
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isMyself() {
        return myself;
    }
}


class UploadFileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public long getSize() {
        return size;
    }
}