package com.librarty.book.controller;

import com.librarty.book.dto.PackageDTO;
import com.librarty.book.dto.response.CommonDataResponseDTO;
import com.librarty.book.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.librarty.book.constance.AppConstance.DetailConstance.PACKAGE_ADDED;
import static com.librarty.book.constance.AppConstance.DetailConstance.PACKAGE_DELETED;

@RestController
@CrossOrigin
@RequestMapping("/v1/package")
public class PackageController {
    @Autowired
    PackageService packageService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity savePackage(@RequestBody PackageDTO packageDTO) {
        packageService.add(packageDTO);
        return new ResponseEntity<>(new CommonDataResponseDTO(true, PACKAGE_ADDED), HttpStatus.OK);
    }

    @DeleteMapping("/{packageId}")
    public ResponseEntity deletePackage(@PathVariable("packageId") Long packageId) {
        packageService.delete(packageId);
        return new ResponseEntity<>(new CommonDataResponseDTO(true, PACKAGE_DELETED), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllPackages() {
        List<PackageDTO> allPackages = packageService.getAll();
        return new ResponseEntity<>(new CommonDataResponseDTO(true, allPackages), HttpStatus.OK);
    }
}
