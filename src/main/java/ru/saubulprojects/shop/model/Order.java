package ru.saubulprojects.shop.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders", schema = "shop")
public class Order {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@CreationTimestamp
	@Column(name = "date_created")
	private LocalDate dateCreated;
	
	@OneToMany(targetEntity = OrderProduct.class, mappedBy = "order", cascade = CascadeType.ALL)
	public Collection<OrderProduct> orderProducts;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "user_id_fk"))
	private User user;
	
	private BigDecimal price;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	public Order(Collection<BasketProduct> basketProducts, User user, OrderStatus status) {
		this.price = BigDecimal.ZERO;
		this.orderProducts = basketProducts.stream()
											 .map(it -> {
												 OrderProduct op = new OrderProduct(this,
													 			  				    it.getProduct(), 
													 			  				    it.getCount(), 
													 			  				    it.getProduct().getPrice().multiply(BigDecimal.valueOf(it.getCount())));
												 this.price.add(op.getPrice());
												 return op;
											 })
										     .collect(Collectors.toList());
		this.user = user;
		this.status = status;
	}
}
