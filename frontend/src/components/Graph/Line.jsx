import { useEffect, useState } from "react";
import Row from "react-bootstrap/Row";
import { useService } from "../../api/Service";
import { Chart as ChartJS, CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend } from "chart.js";
import { Line } from "react-chartjs-2";

ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);

const LineGraph = () => {
  const { getHistory } = useService();
  const [history, setHistory] = useState();

  useEffect(() => {
    async function getHistoryData() {
      try {
        const history = await getHistory();
        setHistory(history);
      } catch (error) {
        console.log(error);
      }
    }
    if (!history) {
      getHistoryData();
    }
  }, [getHistory, history]);

  const options = {
    responsive: true,
    plugins: {
      legend: { display: false },
      title: { display: false },
      tooltip: {
        callbacks: {
          label: function (context) {
            return `$${context.parsed.y.toLocaleString()}`;
          },
        },
      },
    },
    scales: {
      y: {
        ticks: {
          callback: function (value) {
            return `$${value.toLocaleString()}`;
          },
        },
      },
    },
  };

  const data = {
    labels: history?.map((item) =>
      new Date(item.dateTime).toLocaleTimeString([], { hour: "2-digit", minute: "2-digit", second: "2-digit" })
    ),
    datasets: [
      {
        label: "Worth",
        data: history?.map((item) => item.worth),
        borderColor: "rgb(192, 75, 75)",
        backgroundColor: "rgba(192, 75, 75, 0.2)",
        tension: 0.3,
      },
    ],
  };

  return (
    <>
      <div className="line">{history && <Line options={options} data={data} />}</div>
    </>
  );
};

export default LineGraph;
