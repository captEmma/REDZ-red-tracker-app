import "./App.css";
import Investment from "./components/Investment";
import Performance from "./components/Performance";
import Doughnut from "./components/Doughnut";
import ManageStocks from "./components/ManageStocks";

function App() {
    return (
        <>
            {/* TODO: create homepage */}
            <Investment></Investment>
            <Doughnut />
            <ManageStocks />
        </>
    );
}

export default App;
