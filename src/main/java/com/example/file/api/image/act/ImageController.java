package com.example.file.api.image.act;

import com.example.file.api.image.biz.ImageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.core.response.base.dto.ResponseDataDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    public final ImageService imageService;
    @PostMapping("/upload")
    public ResponseDataDTO upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        return ResponseDataDTO.of(imageService.upload(file, request));
    }

    @GetMapping(value = "/show/{imageName}", produces = {
            "image/bmp",
            "image/gif",
            "image/jpeg",
            "image/png",
            "image/svg+xml",
            "image/tiff",
            "image/webp"
    })
    public ResponseEntity<Resource> showImage(@PathVariable("imageName") String fileName) {
        return imageService.showImage(fileName);
    }

//    @GetMapping(value = "/image/show/{imageName}", produces = {
//            "image/bmp",
//            "image/gif",
//            "image/jpeg",
//            "image/png",
//            "image/svg+xml",
//            "image/tiff",
//            "image/webp"
//    })
//    public ResponseEntity<byte[]> userSearch(@PathVariable("imageName") String imageName) throws IOException {
//        System.out.println("image Test");
//        String photoUrl = ImageUtil.getPhotoUrl(imageName);
//        InputStream imageStream = new FileInputStream("C://" + photoUrl);
//        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
//        imageStream.close();
//
//        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
//    }


}
