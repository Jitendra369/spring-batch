CREATE TABLE IF NOT EXISTS online_sale_data (
                                                transaction_id INT PRIMARY KEY,
                                                date DATE,
                                                product_categ VARCHAR(50),
    product_name VARCHAR(50),
    unit_sold INT,
    unit_price FLOAT(8 , 3 ),
    total_revenue DECIMAL,
    region VARCHAR(50),
    payment_method VARCHAR(20)
    );