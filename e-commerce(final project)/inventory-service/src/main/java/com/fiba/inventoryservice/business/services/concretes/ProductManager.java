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

    public ProductManager(ProductRepository _productRepository, CategoryManager _categoryManager) {
        this._productRepository = _productRepository;
        this._categoryManager = _categoryManager;
    }

    /**
     Get product for given id;
     @param productId category id to view.
     @return SuccessDataResult,ErrorDataResult with appropriate message.
     */
    public DataResult<ProductViewDto> getProductWithId(long productId){
        Optional<Product> result = _productRepository.findById(productId);

        if(result.isPresent()){
            return new SuccessDataResult<>(Mapper.productEntityToViewDto(result.get()),Messages.PRODUCT_FOUND);
        }
        return new ErrorDataResult<>(Messages.PRODUCT_NOT_FOUND);
    }

    /**
     View products under given category.
     @param categoryId category to view.
     @return SuccessDataResult,ErrorDataResult with appropriate message.
     */
    public DataResult<List<ProductViewDto>> getProductsByCategoryId(long categoryId){
        List<Product> result = _productRepository.findProductsByCategory(categoryId);
        List<ProductViewDto> resultDto = new ArrayList<>();

        //check category contains any product,if so map to view dto
        if(!result.isEmpty()){
            result.forEach(product -> resultDto.add(Mapper.productEntityToViewDto(product)));
            return new SuccessDataResult<>(resultDto,Messages.PRODUCT_FOUND);
        }
        return new ErrorDataResult<>(Messages.PRODUCT_NOT_FOUND);
    }

    /**
     Add product to database. The category that product belongs
     must exist beforehand.
     @param productInsertionDto product to add.
     @return SuccessResult,ErrorResult with appropriate message.
     */
    public Result addProduct(ProductInsertionDto productInsertionDto){
        Optional<Category> categoryOptional= _categoryManager.findCategoryById(productInsertionDto.getCategoryId());

        //check category exist
        if(categoryOptional.isPresent()){
            Product product = Mapper.productInsertionDtoToEntity(productInsertionDto);
            product.setCategory(categoryOptional.get());

            Product productSaved = _productRepository.save(product);
            return new SuccessDataResult(productSaved.getProductId(),Messages.PRODUCT_ADD_SUCCESS);
        }
        return new ErrorResult(Messages.CATEGORY_NOT_FOUND);


    }
    /**
     Delete product with given id. If deletion successfully returns SuccessResult otherwise returns ErrorResult
     @param productId product id to delete.
     @return SuccessResult,ErrorResult with appropriate message.
     */
    public Result deleteProductWithId(long productId){
        Optional<Product> productOptional = _productRepository.findById(productId);
        if (productOptional.isPresent()){
            Product product = productOptional.get();
            boolean categoryExist = _categoryManager.existById(product.getCategory().getCategoryId());

            if (categoryExist){
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
