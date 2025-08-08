import { faMinus, faPlus } from "@fortawesome/free-solid-svg-icons";
import StockButton from "./StockButton";
import { useEffect, useState } from "react";
import Modal from "./Modal";
import Button from "./Button";
import { useManageFormHook } from "../hooks/ManageFormHook";
import { useService } from "../api/Service";

const ManageStocks = () => {
  const [isBuyingOpen, setIsBuyingOpen] = useState(false);
  const [isSellingOpen, setIsSellingOpen] = useState(false);
  const [companies, setCompanies] = useState([]);

  const { getAllData } = useService();

  useEffect(() => {
    async function getCompaniesData() {
      try {
        const companies = await getAllData();
        const uniqueCompanies = Array.from(new Map(companies.map((company) => [company.symbol, company])).values());
        setCompanies(uniqueCompanies);
      } catch (error) {
        console.log(error.response.data.errors);
      }
    }
    if (companies.length < 1) {
      getCompaniesData();
    }
  }, [companies.length, getAllData]);

  const {
    buyForm: { register: registerBuy, handleSubmit: handleSubmitBuy, errors: buyErrors },
    sellForm: { register: registerSell, handleSubmit: handleSubmitSell, errors: sellErrors },
  } = useManageFormHook({
    onBuySuccess: () => setIsBuyingOpen(false),
    onSellSuccess: () => setIsSellingOpen(false),
  });

  return (
    <>
      <div className="manage-stocks-bg">
        <div>
          <StockButton icon={faPlus} title="Buy shares" onClick={() => setIsBuyingOpen(true)} />
        </div>
        <div className="or center-content">or</div>
        <div>
          <StockButton icon={faMinus} title="Sell shares" onClick={() => setIsSellingOpen(true)} />
        </div>
      </div>
      {isBuyingOpen && (
        <Modal title={"Buy shares"} onClose={() => setIsBuyingOpen(false)}>
          <form onSubmit={handleSubmitBuy}>
            <div className="flex-downward">
              <label htmlFor="symbol">Company</label>
              <select name="symbol" id="symbol" {...registerBuy("symbol")}>
                {companies.map((company) => (
                  <option key={company.symbol} value={company.symbol}>
                    {company.name}
                  </option>
                ))}
              </select>
            </div>

            <div className="flex-downward">
              <label htmlFor="cost">Cost</label>
              <div className="currency">
                <input name="cost" id="cost" type="text" {...registerBuy("cost")} />
              </div>
            </div>
            <Button title="Buy" />
          </form>
        </Modal>
      )}
      {isSellingOpen && (
        <Modal title={"Sell shares"} onClose={() => setIsSellingOpen(false)}>
          <form onSubmit={handleSubmitSell}>
            <div className="flex-downward">
              <label htmlFor="symbol">Company</label>
              <select name="symbol" id="symbol" {...registerSell("symbol")}>
                {companies.map((company) => (
                  <option key={company.symbol} value={company.symbol}>
                    {company.name}
                  </option>
                ))}
              </select>
            </div>

            <div className="flex-downward">
              <label htmlFor="numberOfShares">Number of Shares</label>
              <input name="numberOfShares" id="numberOfShares" type="text" {...registerSell("numberOfShares")} />
            </div>
            <Button title="Sell" />
          </form>
        </Modal>
      )}
    </>
  );
};

export default ManageStocks;
