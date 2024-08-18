FROM python:3.12-slim

WORKDIR /app

# Kopiujemy cały katalog crypto-trading-translation
COPY crypto-trading-translation /app/crypto-trading-translation

# Instalujemy zależności
WORKDIR /app/crypto-trading-translation
RUN pip install --no-cache-dir -r requirements.txt

# Ustawiamy PYTHONPATH
ENV PYTHONPATH=/app

# Uruchamiamy serwis
CMD ["python", "service.py"]