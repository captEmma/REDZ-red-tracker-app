import * as yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";
import { useForm } from "react-hook-form";
import { useService } from "../api/Service";
import { useGlobalContext } from "../context/GlobalContext";

export const useManageFormHook = ({ onBuySuccess, onSellSuccess }) => {
  const { sellShares, buyShares } = useService();
  const { setShouldUpdateInvestments, setShouldUpdateUser, setShouldUpdateMetrics, setShouldUpdateGraph } = useGlobalContext();

  const buySchema = yup.object().shape({
    symbol: yup.string().trim().required("Field required"),
    cost: yup.number().required("Field required"),
  });

  const sellSchema = yup.object().shape({
    symbol: yup.string().trim().required("Field required"),
    numberOfShares: yup.number().required("Field required"),
  });

  const {
    register: registerBuy,
    handleSubmit: handleSubmitBuy,
    formState: { errors: buyErrors },
  } = useForm({
    resolver: yupResolver(buySchema),
    defaultValues: {
      symbol: "",
      cost: "",
    },
  });

  const {
    register: registerSell,
    handleSubmit: handleSubmitSell,
    formState: { errors: sellErrors },
  } = useForm({
    resolver: yupResolver(sellSchema),
    defaultValues: {
      symbol: "",
      numberOfShares: "",
    },
  });

  const onSubmitBuy = async (data) => {
    try {
      console.log("Buying:", data);
      await buyShares(data.symbol, data.cost);
      setShouldUpdateInvestments(true);
      setShouldUpdateUser(true);
      setShouldUpdateMetrics(true);
      setShouldUpdateGraph(true);
      onBuySuccess();
    } catch (error) {
      console.log(error);
    }
  };

  const onSubmitSell = async (data) => {
    try {
      console.log("Selling:", data);
      await sellShares(data.symbol, data.numberOfShares);
      setShouldUpdateInvestments(true);
      setShouldUpdateUser(true);
      setShouldUpdateMetrics(true);
      setShouldUpdateGraph(true);
      onSellSuccess();
    } catch (error) {
      console.log(error);
    }
  };

  return {
    buyForm: {
      register: registerBuy,
      handleSubmit: handleSubmitBuy(onSubmitBuy),
      errors: buyErrors,
    },
    sellForm: {
      register: registerSell,
      handleSubmit: handleSubmitSell(onSubmitSell),
      errors: sellErrors,
    },
  };
};
