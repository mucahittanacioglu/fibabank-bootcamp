package com.fiba.inventoryservice.business.services.concretes;

import com.fiba.inventoryservice.business.Mapper;
import com.fiba.inventoryservice.business.dto.ProductInsertionDto;
import com.fiba.inventoryservice.business.dto.ProductViewDto;
import com.fiba.inventoryservice.business.services.abstracts.ProductService;
import com.fiba.inventoryservice.data.entities.Category;
import com.fiba.inventoryservice.data.entities.Product;
import com.fiba.inventoryservice.data.repositories.ProductRepository;
import com.fiba.inventoryservice.utilities.Messages.Messages;
import com.fiba.inventoryservice.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductManager implements ProductService {
    @Autowired
    private ProductRepository _productRepository;

    @Autowired
    private CategoryManager _categoryManager;

    public DataResult<ProductViewDto> getProductWithId(long productId){
        Optional<Product> result = _productRepository.findById(productId);
        if(result.isPresent()){
            return new SuccessDataResult<>(Mapper.productEntityToViewDto(result.get()),Messages.PRODUCT_FOUND);
        }
        return new ErrorDataResult<>(Messages.PRODUCT_NOT_FOUND);
    }

    public DataResult<List<ProductViewDto>> getProductsByCategoryId(long categoryId){
        List<Product> result = _productRepository.findProductsByCategory(categoryId);
        List<ProductViewDto> resultDto = new ArrayList<>();

        if(!result.isEmpty()){
            result.forEach(product -> resultDto.add(Mapper.productEntityToViewDto(product)));
            return new SuccessDataResult<>(resultDto,Messages.PRODUCT_FOUND);
        }
        return new ErrorDataResult<>(Messages.PRODUCT_NOT_FOUND);
    }
    public Result addProduct(ProductInsertionDto productInsertionDto){
        Optional<Category> categoryOptional= _categoryManager.findCategoryById(productInsertionDto.getCategoryId());
        if(categoryOptional.isPresent()){
            Product product = Mapper.productInsertionDtoToEntity(productInsertionDto);
            product.setCategory(categoryOptional.get());
            _productRepository.save(product);
            return new SuccessResult(Messages.PRODUCT_ADD_SUCCESS);
        }
        return new ErrorResult(Messages.CATEGORY_NOT_FOUND);


    }
    public Result deleteProductWithId(long productId){
        Optional<Product> productOptional = _productRepository.findById(productId);
        if (productOptional.isPresent()){
            Product product = productOptional.get();
            boolean categoryExist = _categoryManager.existById(product.getCategory().getCategoryId());
            if (categoryExist){
                /*Category category = categoryOptional.get();
                product.setCategory(null);
                category.getProductList().remove(product);
                _categoryManager.addCategoryAsEntity(category);*/
                product.getCategory().getProductList().remove(product);
                product.setCategory(null);
                _productRepository.deleteById(productId);
                return new SuccessResult(Messages.PRODUCT_DELETE_SUCCESS);
            }
            return new ErrorResult(Messages.CATEGORY_NOT_FOUND);

        }
        return new ErrorResult(Messages.PRODUCT_NOT_FOUND);
    }


}
