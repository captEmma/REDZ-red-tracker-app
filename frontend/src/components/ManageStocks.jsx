import { faMinus, faPlus } from "@fortawesome/free-solid-svg-icons";
import StockButton from "./StockButton";

const ManageStocks = () => {
    return (
        <>
            <div>
                <StockButton icon={faPlus} title="Buy stocks" />
            </div>
            <div>
                <StockButton icon={faMinus} title="Sell stocks" />
            </div>
        </>
    );
};

export default ManageStocks;
