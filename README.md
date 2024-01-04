clone the project 
run schema.db
change url of sql in application.properties


APIs
/add -> to add a transaction 
** sample **
{
  "amount": 150.0,
  "currency": "USD",
  "timestamp": "2023-05-01",
  "type": "CREDIT",
  "description": "sample transaction"
}

/byTimestamp -> to view for specific date
it requires request param
** sample **
GET /api/transactions/byTimestamp?timestamp=2022-01-01


/by-date-range
**sample**
/GET /api/transactions/by-date-range?startDate=2022-01-01&endDate=2022-01-31

/view
to view all transactions
