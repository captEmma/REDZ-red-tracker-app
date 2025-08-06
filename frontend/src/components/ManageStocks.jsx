import { faMinus, faPlus } from "@fortawesome/free-solid-svg-icons";
import StockButton from "./StockButton";

const ManageStocks = () => {
    return (
        <div className="manage-stocks-bg">
            <div>
                <StockButton icon={faPlus} title="Buy stocks" />
            </div>
            <div className="or center-content">or</div>
            <div>
                <StockButton icon={faMinus} title="Sell stocks" />
            </div>
        </div>
    );
};

export default ManageStocks;
