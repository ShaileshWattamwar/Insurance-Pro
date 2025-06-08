import React from "react";
import { Link, useLocation } from "react-router-dom";

export default function Header({ isLoggedIn, onLogout }) {
  const location = useLocation();

  const navLinks = [
    { to: "/dashboard", label: "Dashboard" },
    { to: "/home", label: "Home Insurance" },
    { to: "/auto", label: "Auto Insurance" },
    { to: "/life", label: "Life Insurance" },
  ];

  return (
    <header className="bg-blue-900 text-white px-6 h-[72px] shadow-md z-50">
      <div className="flex justify-between items-center h-full">
        {/* Brand */}
        <h1 className="text-2xl font-bold">InsureWise</h1>

        {/* Navigation */}
        <nav className="flex h-full">
          {navLinks.map(({ to, label }) => (
            <Link
              key={to}
              to={to}
              className={`relative h-full flex items-center px-6 text-sm font-medium transition-all duration-200
                ${
                  location.pathname === to
                    ? "bg-white/10 text-white font-semibold"
                    : "hover:bg-white/10 hover:text-white-300 text-white"
                }
                hover:mx-1 /* Adds horizontal margin on hover for gap */
              `}
            >
              {label}
            </Link>
          ))}
        </nav>

        {/* Login / Logout */}
        <div className="flex items-center space-x-4 text-sm">
          {isLoggedIn ? (
            <button
              onClick={onLogout}
              className="bg-white text-blue-900 font-semibold px-4 py-2 rounded hover:bg-gray-100"
            >
              Logout
            </button>
          ) : (
            <Link
              to="/login"
              className="relative bg-white text-blue-900 font-semibold px-4 py-2 rounded overflow-hidden"
            >
              <span className="relative z-10">Login</span>
              <span className="absolute inset-0 bg-white/0 hover:bg-black/10 transition-colors rounded"></span>
            </Link>
          )}
        </div>
      </div>
    </header>
  );
}
