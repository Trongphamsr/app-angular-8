package com.phamtrong.app.repository.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.phamtrong.app.dto.CustomerDto;
import com.phamtrong.app.entity.Customer;

@Component
public class CustomerRepositoryCustomImpl extends AbstractRepositoryCustomImpl<CustomerDto> implements CustomerRepositoryCustom{

	@Override
	public Page<Customer> findByConditions(CustomerDto customerDto, Pageable pageable) {
		
		try {

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

			// get count
			CriteriaQuery<Long> criteriaQueryCount = criteriaBuilder.createQuery(Long.class);
			criteriaQueryCount.select(criteriaBuilder.count(criteriaQueryCount.from(Customer.class)));
			conditionsQuery(criteriaBuilder, criteriaQueryCount, customerDto,
					criteriaQueryCount.getRoots().stream().findFirst().orElse(null));
			long countRecords = entityManager.createQuery(criteriaQueryCount).getSingleResult();

			// get data
			List<Customer> listCustomer = null;
			if (countRecords < 1) {
				listCustomer = new ArrayList<>();
			} else {
				CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
				conditionsQuery(criteriaBuilder, criteriaQuery, customerDto, null);

				int firstResult = pageable.getPageNumber() * pageable.getPageSize();
				int maxResults = firstResult + pageable.getPageSize();

				TypedQuery<Customer> typedQuery = entityManager.createQuery(criteriaQuery);
				typedQuery.setFirstResult(firstResult);
				typedQuery.setMaxResults(maxResults);

				listCustomer = typedQuery.getResultList();
			}

			return new PageImpl<>(listCustomer, pageable, countRecords);
		} finally {
			entityManager.close();
		}
		
	}
	
	private void conditionsQuery(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, CustomerDto customerDto,
			Root<?> rootCustomer) {

		if (null == rootCustomer)
			rootCustomer = criteriaQuery.from(Customer.class);
		List<Predicate> conditions = new ArrayList<>();

		if (null != customerDto.getFromCustomerDate()) {
			conditions.add(criteriaBuilder.greaterThanOrEqualTo(rootCustomer.get("customerDate"),
					customerDto.getFromCustomerDate()));
		}

		if (null != customerDto.getToCustomerDate()) {
			conditions.add(
					criteriaBuilder.lessThanOrEqualTo(rootCustomer.get("customerDate"), customerDto.getToCustomerDate()));
		}

		List<Order> orderList = new ArrayList<>();
		orderList.add(criteriaBuilder.desc(rootCustomer.get("id")));

		criteriaQuery.where(conditions.toArray(new Predicate[] {})).orderBy(orderList);
	}

}
