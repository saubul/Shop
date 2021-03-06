package ru.saubulprojects.shop.service;


import java.util.List;

import ru.saubulprojects.shop.model.Basket;
import ru.saubulprojects.shop.model.BasketProduct;
import ru.saubulprojects.shop.model.Product;

public interface BasketProductService {
	
	BasketProduct save(BasketProduct basketProduct);

	List<BasketProduct> findAllByBasketId(Basket basket);

	void delete(BasketProduct basketProduct);

	void deleteBasketProducts(List<BasketProduct> basketProducts);

	BasketProduct findByProductAndBasket(Product product, Basket basket);

	BasketProduct findById(Long id);
	
}
