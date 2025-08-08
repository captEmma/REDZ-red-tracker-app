from datetime import datetime, timedelta

import yfinance as yf
from flask import Flask, jsonify

app = Flask(__name__)

@app.route("/stocks/<period>/<interval>/<stock>")
def stocks(stock, period, interval):
    ticker = yf.Ticker(stock)
  #TODO ticker.info["longName"]
    data = ticker.history(period=period, interval=interval).to_json()

    actions = ticker.actions.to_json()

    data = {
        "data": data,
        "actions": actions}

    return jsonify(data)

if __name__ == "__main__":
    app.run(host="0.0.0.0", debug=True)