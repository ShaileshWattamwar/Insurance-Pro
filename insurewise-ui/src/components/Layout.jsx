import React from "react";
import Header from "./Header";



export default function Layout({ children, isLoggedIn, onLogout }) {
  return (
    <div className="flex flex-col min-h-screen">
      <Header isLoggedIn={isLoggedIn} onLogout={onLogout} />
      <div className="flex flex-1 bg-gray-50">
        
      
        <main className="flex-grow p-8 overflow-auto">{children}</main>
      </div>
      <footer className="bg-blue-900 text-white text-center py-4 mt-4">
        © 2025 InsureWise. All rights reserved.
      </footer>
    </div>
  );
}
