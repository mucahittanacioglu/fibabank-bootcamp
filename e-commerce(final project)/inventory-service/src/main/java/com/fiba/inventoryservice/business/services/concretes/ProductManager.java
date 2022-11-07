package com.fiba.inventoryservice.business.services.concretes;

import com.fiba.inventoryservice.business.Mapper;
import com.fiba.inventoryservice.business.dto.ProductDto;
import com.fiba.inventoryservice.business.services.abstracts.ProductService;
import com.fiba.inventoryservice.data.entities.Product;
import com.fiba.inventoryservice.data.repositories.ProductRepository;
import com.fiba.inventoryservice.utilities.Messages.Messages;
import com.fiba.inventoryservice.utilities.results.DataResult;
import com.fiba.inventoryservice.utilities.results.ErrorDataResult;
import com.fiba.inventoryservice.utilities.results.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductManager implements ProductService {
    @Autowired
    private ProductRepository _productRepository;

    public DataResult<ProductDto> getProductWithId(long productId){
        Optional<Product> result = _productRepository.findById(productId);
        if(result.isPresent()){
            return new SuccessDataResult<>(Mapper.productEntityToDto(result.get()),Messages.PRODUCT_FOUND);
        }
        return new ErrorDataResult<>(Messages.PRODUCT_NOT_FOUND);
    }

    public DataResult<List<ProductDto>> getProductsByCategoryId(long categoryId){
        List<Product> result = _productRepository.findProductsByCategory(categoryId);
        List<ProductDto> resultDto = new ArrayList<>();

        if(!result.isEmpty()){
            result.forEach(product -> resultDto.add(Mapper.productEntityToDto(product)));
            return new SuccessDataResult<>(resultDto,Messages.PRODUCT_FOUND);
        }
        return new ErrorDataResult<>(Messages.PRODUCT_NOT_FOUND);
    }


}
