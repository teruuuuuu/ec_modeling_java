package jp.co.teruuu.ec_modeling.infla.data.dao;

import jp.co.teruuu.ec_modeling.app.app_service.dto.ProductDto;

import java.util.List;

public interface ProductDtoDao {
  List<ProductDto> searchByName(String name, int limit, int offset);
}
