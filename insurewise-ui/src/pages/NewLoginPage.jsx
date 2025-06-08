
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function LoginPage({ onLogin }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    if (email && password) {
      onLogin();
      navigate("/dashboard");
    }
  };

  return (
    
        <div className="flex items-center justify-center p-6">

      <div className="w-full max-w-md bg-white rounded-2xl shadow-2xl p-8 space-y-6">
        <h2 className="text-3xl font-bold text-center text-indigo-700">Login to InsureWise</h2>
        <p className="text-center text-gray-500 text-sm">
          Secure access to manage and buy your insurance policies
        </p>

        <form onSubmit={handleSubmit} className="space-y-5">
          <div>
            <label className="block text-sm font-medium text-gray-700">Email</label>
            <input
              type="email"
              required
              placeholder="you@example.com"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="mt-1 w-full px-4 py-3 border border-gray-300 rounded-lg shadow-sm focus:ring-2 focus:ring-indigo-500 focus:outline-none"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700">Password</label>
            <input
              type="password"
              required
              placeholder="••••••••"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="mt-1 w-full px-4 py-3 border border-gray-300 rounded-lg shadow-sm focus:ring-2 focus:ring-indigo-500 focus:outline-none"
            />
          </div>

          <div className="flex justify-between text-sm text-indigo-600 font-medium">
            <button
              type="button"
              className="hover:underline"
              onClick={() => alert("Reset flow not implemented yet")}
            >
              Forgot password?
            </button>
            <button
              type="button"
              className="hover:underline"
              onClick={() => navigate("/register")}
            >
              Create account
            </button>
          </div>

          <button
            type="submit"
            className="w-full py-3 bg-indigo-600 text-white font-semibold rounded-lg shadow-md hover:bg-indigo-700 transition"
          >
            Login
          </button>
        </form>

        <p className="text-xs text-center text-gray-400 mt-4">
          By logging in, you agree to our{" "}
          <span className="underline text-indigo-600 cursor-pointer">Terms</span> and{" "}
          <span className="underline text-indigo-600 cursor-pointer">Privacy Policy</span>.
        </p>
      </div>
    </div>
  );
}
