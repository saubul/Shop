package ru.saubulprojects.shop.model;

import java.math.BigDecimal;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders_products", schema = "shop")
public class OrderProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(targetEntity = Order.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "order_id_fk"))
	private Order order;
	
	@ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "product_id_fk"))
	private Product product;
	
	private int count;
	
	private BigDecimal price;
	
	public OrderProduct(Order order, Product product, int count, BigDecimal price) {
		this.order = order;
		this.product = product;
		this.count = count;
		this.price = price;
	}
}
