package main

import (
	"encoding/json"
	"log"
	"net/http"
	"strconv"

	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

// Payment represents the payment entity stored in the DB.
type Payment struct {
	ID     int     `json:"id" gorm:"primaryKey;autoIncrement"`
	Amount float64 `json:"amount"`
	Status string  `json:"status"`
}

var db *gorm.DB

// initDB initializes the Postgres connection and migrates the Payment schema.
func initDB() {
	// Customize these connection parameters as needed.
	dsn := "host=localhost user=youruser password=yourpassword dbname=payment_db port=5432 sslmode=disable TimeZone=UTC"
	var err error
	db, err = gorm.Open(postgres.Open(dsn), &gorm.Config{})
	if err != nil {
		log.Fatal("failed to connect to database:", err)
	}

	// Automatically create the payments table if it doesn't exist.
	if err := db.AutoMigrate(&Payment{}); err != nil {
		log.Fatal("failed to migrate database:", err)
	}
}

// getPayments retrieves all payments from the database.
func getPayments(w http.ResponseWriter, r *http.Request) {
	var payments []Payment
	if result := db.Find(&payments); result.Error != nil {
		http.Error(w, result.Error.Error(), http.StatusInternalServerError)
		return
	}
	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(payments)
}

// createPayment decodes the request and creates a new payment record.
func createPayment(w http.ResponseWriter, r *http.Request) {
	var payment Payment
	if err := json.NewDecoder(r.Body).Decode(&payment); err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	// Set status to "Pending" for new payments.
	payment.Status = "Pending"

	if result := db.Create(&payment); result.Error != nil {
		http.Error(w, result.Error.Error(), http.StatusInternalServerError)
		return
	}
	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(payment)
}

func main() {
	initDB()

	http.HandleFunc("/payments", func(w http.ResponseWriter, r *http.Request) {
		switch r.Method {
		case http.MethodGet:
			getPayments(w, r)
		case http.MethodPost:
			createPayment(w, r)
		default:
			http.Error(w, "Method not allowed", http.StatusMethodNotAllowed)
		}
	})

	port := 8086
	log.Printf("Payment Service running on port %d", port)
	log.Fatal(http.ListenAndServe(":"+strconv.Itoa(port), nil))
}
