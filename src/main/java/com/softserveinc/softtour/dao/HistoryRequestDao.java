package dao;


import User.User;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface HistoryRequestDao {

    void save(String cityFrom, Date dateFrom, Date dateTo, int daysFrom, int daysTo, Set<String> stars, int adults, int children,
              BigDecimal priceFrom, BigDecimal priceTo, User user, Country country,Date dateReq);
    void update(long id, String cityFrom, Date dateFrom, Date dateTo, int daysFrom, int daysTo, Set<String> stars, int adults, int children,
                BigDecimal priceFrom, BigDecimal priceTo, User user, Country country,Date dateReq);
    void delete(long id);

    CountryDao findById(long id);
    List<CountryDao> findByName(String name);
    List<CountryDao> findByUser(User user);
    List<CountryDao> findByStars(int... stars);
    List<CountryDao> findByCountry(Country country);
    List<CountryDao> findByDateReq(Date dateReq);

    List<CountryDao> getAll();

}
