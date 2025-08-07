import { faMinus, faPlus } from "@fortawesome/free-solid-svg-icons";
import StockButton from "./StockButton";

const ManageStocks = ({ setIsBuyingOpen, setIsSellingOpen }) => {
  return (
    <div className="manage-stocks-bg">
      <div>
        <StockButton icon={faPlus} title="Buy shares" onClick={() => setIsBuyingOpen(true)} />
      </div>
      <div className="or center-content">or</div>
      <div>
        <StockButton icon={faMinus} title="Sell shares" onClick={() => setIsSellingOpen(true)} />
      </div>
    </div>
  );
};

export default ManageStocks;
