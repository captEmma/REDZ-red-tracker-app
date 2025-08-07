import { useState } from "react";
import "./App.css";
import Investment from "./components/Investment";
import ManageStocks from "./components/ManageStocks";
import Modal from "./components/Modal";
import Button from "./components/Button";

function App() {
  const [isBuyingOpen, setIsBuyingOpen] = useState(false);
  const [isSellingOpen, setIsSellingOpen] = useState(false);

  const options = ["Company1", "Company2", "Company3"];

  return (
    <>
      <Investment />
      <ManageStocks setIsBuyingOpen={setIsBuyingOpen} setIsSellingOpen={setIsSellingOpen} />
      {isBuyingOpen && (
        <Modal title={"Buy shares"} onClose={() => setIsBuyingOpen(false)}>
          <form>
            <div className="flex-downward">
              <label htmlFor="symbol">Company</label>
              <select name="symbol" id="symbol">
                {options.map((option) => (
                  <option value={option}>{option}</option>
                ))}
              </select>
            </div>

            <div className="flex-downward">
              <label htmlFor="amount">Amount</label>
              <div className="currency">
                <input name="amount" id="amount" type="text" />
              </div>
            </div>
            <Button title="Buy" />
          </form>
        </Modal>
      )}
      {isSellingOpen && (
        <Modal title={"Sell shares"} onClose={() => setIsSellingOpen(false)}>
          <form>
            <div className="flex-downward">
              <label htmlFor="symbol">Company</label>
              <select name="symbol" id="symbol">
                {options.map((option) => (
                  <option value={option}>{option}</option>
                ))}
              </select>
            </div>

            <div className="flex-downward">
              <label htmlFor="shares">Amount of Shares</label>
              <input name="shares" id="shares" type="text" />
            </div>
            <Button title="Sell" />
          </form>
        </Modal>
      )}
    </>
  );
}

export default App;
