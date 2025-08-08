/* eslint-disable react-refresh/only-export-components */
import { createContext, useContext, useState } from "react";

export const GlobalContext = createContext({
  shouldUpdateInvestments: false,
  setShouldUpdateInvestments: () => {
    throw new Error("Context is not initialised");
  },
  shouldUpdateUser: false,
  setShouldUpdateUser: () => {
    throw new Error("Context is not initialised");
  },
});

export const GlobalContextProvider = ({ children }) => {
  const [shouldUpdateInvestments, setShouldUpdateInvestments] = useState(false);
  const [shouldUpdateUser, setShouldUpdateUser] = useState();

  const contextValue = { shouldUpdateInvestments, setShouldUpdateInvestments, shouldUpdateUser, setShouldUpdateUser };

  return <GlobalContext.Provider value={contextValue}> {children}</GlobalContext.Provider>;
};

export const useGlobalContext = () => useContext(GlobalContext);
