# cashRichAssignment

As per task created 4 Apis
1. Sign-up User 
2. Login User
3. Update User  (Authenticated Api)
4. Fetch Latest Cryptocurrency Quotes (Authenticated Api)


Required Headers 
1. application   -   CASH_RICH
2. channel       -   APP



# Curl

1. Sign-up ::
   curl --location 'http://localhost:8080/cash-rich/api/user/sign-up' \
   --header 'application: CASH_RICH' \
   --header 'channel: APP' \
   --header 'Content-Type: application/json' \
   --header 'Cookie: JSESSIONID=FEA925485A60FF75C16AC4DA8703AFC8' \
   --data-raw '{
   "username": "username1234",
   "password": "Pankaj12@",
   "firstName": "Pankaj",
   "lastName": "Rautel",
   "email": "abc6@gmail.com",
   "mobileNo": "1234"
   }'


   response ::
   {
   "message": "User created successfully",
   "statusCode": 200,
   "result": {
   "id": 7,
   "firstName": "Pankaj",
   "lastName": "Rautel",
   "email": "abc6@gmail.com",
   "mobileNo": "1234",
   "username": "username1234",
   "password": "Pankaj12@"
   }
   }


2. Login User ::
   curl --location 'http://localhost:8080/cash-rich/api/user/login' \
   --header 'application: CASH_RICH' \
   --header 'channel: APP' \
   --header 'Content-Type: application/json' \
   --header 'Cookie: JSESSIONID=FEA925485A60FF75C16AC4DA8703AFC8' \
   --data-raw '{
   "username": "username",
   "password": "Pankaj12@"
   }'



   response ::
   {
   "message": "Login successfully",
   "statusCode": 200,
   "result": {
   "token": "jh2bFVbTaF9VMBcaLg3LaTTDnLr8V1QUusername7Flapuocs6uVq2eC7Ru0uFEFmQoxtzAx"
   }
   }

3. Update User ::
   curl --location 'http://localhost:8080/cash-rich/api/user/update' \
   --header 'Authorization: Bearer jh2bFVbTaF9VMBcaLg3LaTTDnLr8V1QUusername7Flapuocs6uVq2eC7Ru0uFEFmQoxtzAx' \
   --header 'application: CASH_RICH' \
   --header 'channel: APP' \
   --header 'Content-Type: application/json' \
   --header 'Cookie: JSESSIONID=FEA925485A60FF75C16AC4DA8703AFC8' \
   --data-raw '{
   "username": "username",
   "password": "Pankaj12@",
   "firstName": "Pankaj",
   "lastName": "Rautel",
   "email": "abc@gmail.com",
   "mobileNo": "1234"
   }'

rsponse ::
{
"message": "User updated successfully",
"statusCode": 200,
"result": "Success"
}




4. Fetch Latest Cryptocurrency Quotes ::
   curl --location 'http://localhost:8080/cash-rich/api/latest/crypto-quotes/fetch' \
   --header 'Authorization: Bearer jh2bFVbTaF9VMBcaLg3LaTTDnLr8V1QUusername7Flapuocs6uVq2eC7Ru0uFEFmQoxtzAx' \
   --header 'application: CASH_RICH' \
   --header 'channel: APP' \
   --header 'Content-Type: application/json' \
   --header 'Cookie: JSESSIONID=FEA925485A60FF75C16AC4DA8703AFC8' \
   --data '{
   "userId": 1,
   "cryptSymbol": "BTC"
   }'

response ::

{
"message": "Fetch data successfully",
"statusCode": 200,
"result": {
"data": {
"BTC": {
"symbol": "BTC",
"circulating_supply": 19731868,
"last_updated": "2024-07-27T14:55:00.000Z",
"is_active": 1,
"total_supply": 19731868,
"tvl_ratio": null,
"cmc_rank": 1,
"self_reported_circulating_supply": null,
"platform": null,
"tags": [
"mineable",
"pow",
"sha-256",
"store-of-value",
"state-channel",
"coinbase-ventures-portfolio",
"three-arrows-capital-portfolio",
"polychain-capital-portfolio",
"binance-labs-portfolio",
"blockchain-capital-portfolio",
"boostvc-portfolio",
"cms-holdings-portfolio",
"dcg-portfolio",
"dragonfly-capital-portfolio",
"electric-capital-portfolio",
"fabric-ventures-portfolio",
"framework-ventures-portfolio",
"galaxy-digital-portfolio",
"huobi-capital-portfolio",
"alameda-research-portfolio",
"a16z-portfolio",
"1confirmation-portfolio",
"winklevoss-capital-portfolio",
"usv-portfolio",
"placeholder-ventures-portfolio",
"pantera-capital-portfolio",
"multicoin-capital-portfolio",
"paradigm-portfolio",
"bitcoin-ecosystem",
"ftx-bankruptcy-estate"
],
"date_added": "2010-07-13T00:00:00.000Z",
"quote": {
"USD": {
"fully_diluted_market_cap": 1446729386643.75,
"last_updated": "2024-07-27T14:55:00.000Z",
"market_cap_dominance": 55.4832,
"tvl": null,
"percent_change_30d": 11.64959517,
"percent_change_1h": -0.49608361,
"percent_change_24h": 2.57945908,
"market_cap": 1359365394713.1145,
"volume_change_24h": -31.4587,
"price": 68891.8755544642,
"percent_change_60d": 1.33813528,
"volume_24h": 23457513043.28575,
"percent_change_90d": 8.40798992,
"percent_change_7d": 3.4012245
}
},
"num_market_pairs": 11617,
"infinite_supply": false,
"name": "Bitcoin",
"max_supply": 21000000,
"id": 1,
"self_reported_market_cap": null,
"is_fiat": 0,
"slug": "bitcoin"
}
},
"status": {
"error_message": null,
"elapsed": 35,
"credit_count": 1,
"error_code": 0,
"timestamp": "2024-07-27T14:56:10.157Z",
"notice": null
}
}
}