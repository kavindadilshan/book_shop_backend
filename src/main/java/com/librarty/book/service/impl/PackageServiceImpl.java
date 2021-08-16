package com.librarty.book.service.impl;

import com.librarty.book.dto.BookDTO;
import com.librarty.book.dto.PackageDTO;
import com.librarty.book.entity.Book;
import com.librarty.book.entity.Package;
import com.librarty.book.exceptions.BookServiceException;
import com.librarty.book.repository.PackageRepository;
import com.librarty.book.service.PackageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import static com.librarty.book.constance.AppConstance.DuplicatedConstance.PACKAGE_ALREADY_EXISTS;
import static com.librarty.book.constance.AppConstance.ErrorCodeConstance.RESOURCE_ALREADY_EXISTS;
import static com.librarty.book.constance.AppConstance.ErrorCodeConstance.RESOURCE_NOT_FOUND;
import static com.librarty.book.constance.AppConstance.NotFoundConstance.BOOK_NOT_FOUND;

@Service
public class PackageServiceImpl implements PackageService {

    private static final Logger LOGGER = LogManager.getLogger(PackageService.class);

    @Autowired
    PackageRepository packageRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void add(PackageDTO packageDTO) {
        try {
            LOGGER.info("Execute Function : save package");
            Optional<Package> optionalPackageEntity = packageRepository.findById(packageDTO.getPackageId());
            if (optionalPackageEntity.isPresent())
                throw new BookServiceException(RESOURCE_ALREADY_EXISTS,PACKAGE_ALREADY_EXISTS);
            Package packageEntity = new Package();
            packageEntity.setId(packageDTO.getPackageId());
            packageEntity.setPackageName(packageDTO.getPackageName());
            packageEntity.setPrice(packageDTO.getPrice());

            packageRepository.save(packageEntity);

        }catch (Exception e){
            LOGGER.error("Function : savePackage "+e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public void update(PackageDTO packageDTO) {

    }

    @Override
    public Optional<Book> get(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        try {
            LOGGER.info("Execute Function : delete package");
            Optional<Package> optionalPackage = packageRepository.findById(id);
            if (!optionalPackage.isPresent())
                throw new BookServiceException(RESOURCE_NOT_FOUND, BOOK_NOT_FOUND);

        }catch (Exception e){
            LOGGER.error("Function:deletePackage:"+e.getMessage());
            throw e;
        }
    }

    @Override
    public List<PackageDTO> getAll() {
        List <Package> all = packageRepository.findAll();
        return getPackageDTO(all);
    }

    private List<PackageDTO> getPackageDTO(List<Package>packageList){
        Type targetType = new TypeToken<List<PackageDTO>>() {
        }.getType();
        return modelMapper.map(packageList, targetType);
    }
}
