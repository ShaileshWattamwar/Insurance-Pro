import React, { useState } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Layout from "./components/Layout";
import Dashboard from "./pages/Dashboard";
import HomeInsurance from "./pages/HomeInsurance";
import AutoInsurance from "./pages/AutoInsurance";
import LifeInsurance from "./pages/LifeInsurance";
import NewLoginPage from "./pages/NewLoginPage";

export default function App() {
  const [loggedIn, setLoggedIn] = useState(false);

  return (
    <Router>
      <Routes>
        <Route
          path="/"
          element={
            <Layout isLoggedIn={loggedIn} onLogout={() => setLoggedIn(false)}>
              <Dashboard />
            </Layout>
          }
        />
        <Route
          path="/dashboard"
          element={
            <Layout isLoggedIn={loggedIn} onLogout={() => setLoggedIn(false)}>
              <Dashboard />
            </Layout>
          }
        />
        <Route
          path="/home"
          element={
            <Layout isLoggedIn={loggedIn} onLogout={() => setLoggedIn(false)}>
              <HomeInsurance />
            </Layout>
          }
        />
        <Route
          path="/auto"
          element={
            <Layout isLoggedIn={loggedIn} onLogout={() => setLoggedIn(false)}>
              <AutoInsurance />
            </Layout>
          }
        />
        <Route
          path="/life"
          element={
            <Layout isLoggedIn={loggedIn} onLogout={() => setLoggedIn(false)}>
              <LifeInsurance />
            </Layout>
          }
        />
        
        <Route
          path="/login"
          element={
          <Layout isLoggedIn={false} onLogout={() => {}}>
          <NewLoginPage onLogin={() => setLoggedIn(true)} />
          </Layout>
        }
/>

      </Routes>
    </Router>
  );
}
