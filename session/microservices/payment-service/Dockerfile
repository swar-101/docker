FROM golang:1.23-alpine AS builder

WORKDIR /app

# Copy dependency specifications first for layer caching
COPY go.mod go.sum ./
RUN go mod download

# Copy source code
COPY . .

# Update module files
RUN go mod tidy

# Build static binary for Alpine
RUN CGO_ENABLED=0 go build -ldflags="-w -s" -o /app/payment-service

# Final stage
FROM alpine:3.20
RUN apk --no-cache add ca-certificates
WORKDIR /app
COPY --from=builder /app/payment-service /app/payment-service

EXPOSE 8086
CMD ["/app/payment-service"]