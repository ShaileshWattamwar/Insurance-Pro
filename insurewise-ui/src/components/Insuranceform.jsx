import React, { useState } from "react";

export default function InsuranceForm({ type }) {
  const [formData, setFormData] = useState({
    age: "",
    amount: "",
    location: "",
  });

  const [premium, setPremium] = useState(null);

  const handleChange = (e) =>
    setFormData({ ...formData, [e.target.name]: e.target.value });

  const calculatePremium = () => {
    // Placeholder for real logic or API call
    const base = 5000;
    const ageFactor = formData.age ? parseInt(formData.age, 10) * 10 : 0;
    const amountFactor = formData.amount ? parseInt(formData.amount, 10) / 100 : 0;
    const calculated = base + ageFactor + amountFactor;
    setPremium(`₹${calculated.toFixed(2)}`);
  };

  return (
    <div className="max-w-md mx-auto bg-white p-8 rounded-lg shadow-lg">
      <h2 className="text-3xl font-bold text-indigo-700 mb-6">{type} Insurance Premium Calculator</h2>

      <label className="block mb-3 font-semibold text-gray-700">
        Age
        <input
          type="number"
          name="age"
          value={formData.age}
          onChange={handleChange}
          placeholder="Enter your age"
          className="w-full mt-1 px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-indigo-500"
        />
      </label>

      <label className="block mb-3 font-semibold text-gray-700">
        Sum Assured (₹)
        <input
          type="number"
          name="amount"
          value={formData.amount}
          onChange={handleChange}
          placeholder="Enter sum assured"
          className="w-full mt-1 px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-indigo-500"
        />
      </label>

      <label className="block mb-6 font-semibold text-gray-700">
        Location
        <input
          type="text"
          name="location"
          value={formData.location}
          onChange={handleChange}
          placeholder="Enter your location"
          className="w-full mt-1 px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-indigo-500"
        />
      </label>

      <button
        onClick={calculatePremium}
        className="w-full bg-indigo-600 hover:bg-indigo-700 text-white py-3 rounded-md font-semibold transition"
      >
        Calculate Premium
      </button>

      {premium && (
        <p className="mt-6 text-center text-xl font-bold text-green-700">
          Estimated Premium: {premium}
        </p>
      )}
    </div>
  );
}
