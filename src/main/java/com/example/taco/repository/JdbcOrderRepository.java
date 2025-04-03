/*package com.example.taco.repository;

import com.example.taco.data.IngredientRef;
import com.example.taco.data.Taco;
import com.example.taco.data.TacoOrder;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

 @Repository
public class JdbcOrderRepository implements OrderRepository {
    private final JdbcOperations jdbcOperations;

    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }
    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

    @Override
    @Transactional
    public TacoOrder save(TacoOrder tacoOrder) {
        PreparedStatementCreatorFactory ps = new PreparedStatementCreatorFactory(
                "insert into Taco_Order "
                + "(DELIVERY_NAME, DELIVERY_STREET, DELIVERY_CITY,"
                + "DELIVERY_STATE,DELIVERY_ZIP,CC_NUMBER, "
                + "CC_EXPIRATION,CC_CVV,PLACED_AT)"
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,
                Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,
                Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP
        );

        ps.setReturnGeneratedKeys(true);

        tacoOrder.setPlacedAt(new Date());
        PreparedStatementCreator psc =
                ps.newPreparedStatementCreator(
                        Arrays.asList(
                                tacoOrder.getDeliveryName(),
                                tacoOrder.getDeliveryStreet(),
                                tacoOrder.getDeliveryCity(),
                                tacoOrder.getDeliveryState(),
                                tacoOrder.getDeliveryZip(),
                                tacoOrder.getCcNumber(),
                                tacoOrder.getCcExpiration(),
                                tacoOrder.getCcCVV(),
                                tacoOrder.getPlacedAt()));
        
        jdbcOperations.update(psc, keyHolder);

        Number key = keyHolder.getKey();
        if(key != null) {
            tacoOrder.setId(key.longValue());
        }else {
            throw new IllegalStateException("Order ID do not generation");
        }

         long orderId = key.longValue();

        List<Taco> tacos = tacoOrder.getTacos();
        int i = 0;
        for (Taco taco : tacos) {
            saveTaco(orderId,i++,taco);
        }
        return tacoOrder;
    }

    private void saveTaco(Long orderId, int orderKey, Taco taco){
        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory ps =
                new PreparedStatementCreatorFactory(
                        "insert into TACO"
                                + "(name, CREATED_AT, TACO_ORDER, TACO_ORDER_KEY) "
                                + "values ( ?,?,?,? )",
                        Types.VARCHAR, Types.TIMESTAMP, Types.LONGVARCHAR, Types.LONGVARCHAR
                );
        ps.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc =
                ps.newPreparedStatementCreator(
                        Arrays.asList(
                                taco.getName(),
                                taco.getCreatedAt(),
                                orderId,
                                orderKey));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        Number key = keyHolder.getKey();
        if(key != null) {
            taco.setId(key.longValue());
        }else {
            throw new IllegalStateException("Order ID do not generation");
        }
        long tacoId = key.longValue();

        List<IngredientRef> ingredientRefs = taco.getIngredients()
                .stream()
                .map(ingredient -> new IngredientRef(ingredient.getId()))
                .toList();
        
        saveIngredientRefs(tacoId, ingredientRefs);
        
    }
    
    private void saveIngredientRefs(long tacoId, List<IngredientRef> ingredients) {
        int key = 0;
        for (IngredientRef ingredientRef : ingredients) {
            jdbcOperations.update(
                    "insert into INGREDIENT_REF (INGREDIENT, TACO, TACO_KEY) "
                    + "values ( ?,?,? )",
                    ingredientRef.getIngredient(), tacoId, key
            );
        }
    }
}
*/