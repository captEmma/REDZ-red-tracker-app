import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faDollarSign } from "@fortawesome/free-solid-svg-icons";

const StockButton = ({ title, icon, ...rest }) => {
    return (
        <div className="center-content">
            <button className="stock-button" {...rest}>
                <FontAwesomeIcon size="2xs" icon={icon} className="px-0" />
                <FontAwesomeIcon icon={faDollarSign} />
                {title}
            </button>
        </div>
    );
};

export default StockButton;
