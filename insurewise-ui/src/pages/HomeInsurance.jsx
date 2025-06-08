
// import React, { useState } from "react";
// import axios from "axios";

// function HomeInsurance() {
//   const [formData, setFormData] = useState({
//     policyHolderName: "",
//     city: "",
//     buildingValue: "",
//     contentValue: "",
//     carpetArea: "",
//     buildingType: "",
//     earthquakeCover: false,
//     floodCover: false,
//   });

//   const [responseData, setResponseData] = useState(null);
//   const [error, setError] = useState("");
//   const [buttonPressed, setButtonPressed] = useState(false);

//   const handleChange = (e) => {
//     const { name, value, type, checked } = e.target;
//     setFormData((prev) => ({
//       ...prev,
//       [name]: type === "checkbox" ? checked : value,
//     }));
//   };

//   const handleSubmit = async (e) => {
//     e.preventDefault();
//     try {
//       const response = await axios.post(
//         "http://localhost:8080/api/homepolicy/calculatePremium",
//         formData
//       );
//       setResponseData(response.data);
//       setError("");
//     } catch (err) {
//       setError("Something went wrong. Please try again later.");
//       setResponseData(null);
//     }
//   };

//   return (
//     <div style={page}>
//       {/* Left - Dashboard Section */}
//       <div style={leftPanel}>
//         <h2 style={sectionTitle}>🏠 Insurance Overview</h2>
//         <ul style={list}>
//           <li>• Protects your home structure & contents.</li>
//           <li>• Optional natural disaster cover.</li>
//           <li>• Customizable based on your building type.</li>
//         </ul>

//         <h3 style={sectionTitle}>📌 Quick Tips</h3>
//         <ul style={list}>
//           <li>✔️ Estimate correct building & content value.</li>
//           <li>✔️ Include flood/earthquake for high-risk areas.</li>
//           <li>✔️ Recalculate yearly to stay protected.</li>
//         </ul>

//         <h3 style={sectionTitle}>📊 Example Coverage</h3>
//         <p style={coverageBox}>
//           <strong>Premium: ₹5,400/year</strong><br />
//           Building: ₹50L<br />
//           Contents: ₹10L<br />
//           Covers: Fire, Theft, Earthquake
//         </p>
//       </div>

//       {/* Right - Calculator */}
//       <div style={rightPanel}>
//         <h1 style={title}>Home Insurance Premium Calculator</h1>

//         <form onSubmit={handleSubmit} style={form}>
//           <FloatingInput
//             label="Policy Holder Name"
//             name="policyHolderName"
//             value={formData.policyHolderName}
//             onChange={handleChange}
//             required
//           />
//           <FloatingInput
//             label="City"
//             name="city"
//             value={formData.city}
//             onChange={handleChange}
//             required
//           />
//           <FloatingInput
//             label="Building Value (₹)"
//             name="buildingValue"
//             type="number"
//             value={formData.buildingValue}
//             onChange={handleChange}
//             required
//           />
//           <FloatingInput
//             label="Content Value (₹)"
//             name="contentValue"
//             type="number"
//             value={formData.contentValue}
//             onChange={handleChange}
//             required
//           />
//           <FloatingInput
//             label="Carpet Area (sq.ft)"
//             name="carpetArea"
//             type="number"
//             value={formData.carpetArea}
//             onChange={handleChange}
//             required
//           />

//           <div style={floatingSelectWrapper}>
//             <select
//               name="buildingType"
//               value={formData.buildingType}
//               onChange={handleChange}
//               required
//               style={floatingSelect}
//             >
//               <option value="" disabled></option>
//               <option value="Apartment">Apartment</option>
//               <option value="Independent House">Independent House</option>
//               <option value="Villa">Villa</option>
//               <option value="Other">Other</option>
//             </select>
//             <label style={floatingSelectLabel}>Building Type</label>
//           </div>

//           <div style={togglesWrapper}>
//             <ToggleSwitch
//               label="Earthquake Cover"
//               name="earthquakeCover"
//               checked={formData.earthquakeCover}
//               onChange={handleChange}
//             />
//             <ToggleSwitch
//               label="Flood Cover"
//               name="floodCover"
//               checked={formData.floodCover}
//               onChange={handleChange}
//             />
//           </div>

//           <button
//             type="submit"
//             style={{ ...button, ...(buttonPressed ? buttonActive : {}) }}
//             onMouseDown={() => setButtonPressed(true)}
//             onMouseUp={() => setButtonPressed(false)}
//             onMouseLeave={() => setButtonPressed(false)}
//           >
//             Calculate Premium
//           </button>
//         </form>

//         {responseData && (
//           <div style={responseBox}>
//             <h2>Premium Details</h2>
//             <DetailRow label="Policy ID" value={responseData.policyId} />
//             <DetailRow label="Holder" value={responseData.policyHolderName} />
//             <DetailRow label="Risk" value={responseData.locationRisk} />
//             <DetailRow label="Premium" value={`₹${responseData.premium}`} />
//             <DetailRow label="Start Date" value={responseData.startDate} />
//             <DetailRow label="End Date" value={responseData.endDate} />
//           </div>
//         )}

//         {error && <p style={errorStyle}>{error}</p>}
//       </div>
//     </div>
//   );
// }

// // COMPONENTS

// function FloatingInput({ label, name, type = "text", value, onChange, required }) {
//   const [focused, setFocused] = useState(false);
//   const shouldFloat = focused || value;

//   return (
//     <div style={floatingInputWrapper}>
//       <input
//         style={{
//           ...floatingInput,
//           borderColor: shouldFloat ? "#2563eb" : "#ccc",
//         }}
//         type={type}
//         name={name}
//         value={value}
//         onChange={onChange}
//         onFocus={() => setFocused(true)}
//         onBlur={() => setFocused(false)}
//         placeholder=" "
//         required={required}
//       />
//       <label
//         style={{
//           ...floatingLabel,
//           top: shouldFloat ? 4 : "50%",
//           fontSize: shouldFloat ? 12 : 16,
//           transform: shouldFloat ? "translateY(0)" : "translateY(-50%)",
//           color: shouldFloat ? "#2563eb" : "#999",
//         }}
//       >
//         {label}
//       </label>
//     </div>
//   );
// }

// function ToggleSwitch({ label, name, checked, onChange }) {
//   return (
//     <label style={toggleLabel}>
//       <input
//         type="checkbox"
//         name={name}
//         checked={checked}
//         onChange={onChange}
//         style={{ display: "none" }}
//       />
//       <span style={{ ...toggleSlider, ...(checked ? toggleSliderChecked : {}) }}>
//         <span
//           style={{
//             ...knobStyle,
//             transform: checked ? "translateX(22px)" : "translateX(0)",
//           }}
//         />
//       </span>
//       <span style={toggleText}>{label}</span>
//     </label>
//   );
// }

// function DetailRow({ label, value }) {
//   return (
//     <p style={{ margin: "6px 0", fontSize: 15 }}>
//       <strong>{label}:</strong> {value}
//     </p>
//   );
// }

// // STYLES

// const page = {
//   display: "flex",
//   padding: "2rem",
//   gap: "2rem",
//   fontFamily: "'Segoe UI', sans-serif",
// };

// const leftPanel = {
//   flex: 1,
//   background: "#f0f4f8",
//   padding: "2rem",
//   borderRadius: 16,
//   boxShadow: "0 4px 12px rgba(0,0,0,0.05)",
// };

// const rightPanel = {
//   flex: 1.3,
//   background: "#fff",
//   padding: "2rem",
//   borderRadius: 16,
//   boxShadow: "0 4px 12px rgba(0,0,0,0.1)",
// };

// const title = {
//   fontSize: 24,
//   fontWeight: 700,
//   color: "#003366",
//   marginBottom: 24,
//   textAlign: "center",
// };

// const form = {
//   display: "flex",
//   flexDirection: "column",
//   gap: 20,
// };

// const floatingInputWrapper = { position: "relative" };
// const floatingInput = {
//   width: "100%",
//   padding: "14px 16px",
//   fontSize: 16,
//   borderRadius: 10,
//   border: "2px solid #ccc",
//   outline: "none",
// };
// const floatingLabel = {
//   position: "absolute",
//   left: 18,
//   top: "50%",
//   pointerEvents: "none",
//   transition: "all 0.3s ease",
//   backgroundColor: "#fff",
//   padding: "0 4px",
// };

// const floatingSelectWrapper = { position: "relative" };
// const floatingSelect = {
//   width: "100%",
//   padding: "14px 16px",
//   fontSize: 16,
//   borderRadius: 10,
//   border: "2px solid #ccc",
//   backgroundColor: "#fff",
// };
// const floatingSelectLabel = {
//   position: "absolute",
//   top: "-10px",
//   left: 16,
//   fontSize: 13,
//   color: "#777",
//   backgroundColor: "#fff",
//   padding: "0 6px",
// };

// const togglesWrapper = { display: "flex", gap: 24 };
// const toggleLabel = { display: "flex", alignItems: "center", gap: 12 };
// const toggleSlider = {
//   width: 48,
//   height: 26,
//   borderRadius: 13,
//   backgroundColor: "#ccc",
//   position: "relative",
//   transition: "background 0.3s ease",
// };
// const toggleSliderChecked = { backgroundColor: "#2563eb" };
// const knobStyle = {
//   position: "absolute",
//   top: 2,
//   left: 2,
//   width: 22,
//   height: 22,
//   borderRadius: "50%",
//   backgroundColor: "#fff",
//   transition: "transform 0.3s ease",
// };
// const toggleText = { fontSize: 15 };

// const button = {
//   padding: "14px",
//   fontSize: 16,
//   borderRadius: 10,
//   border: "none",
//   backgroundColor: "#007bff",
//   color: "#fff",
//   fontWeight: 600,
//   cursor: "pointer",
//   transition: "transform 0.1s ease",
// };
// const buttonActive = { transform: "scale(0.97)" };

// const responseBox = {
//   marginTop: 24,
//   padding: 20,
//   backgroundColor: "#e0f2fe",
//   borderRadius: 12,
// };

// const errorStyle = {
//   marginTop: 16,
//   color: "#d00000",
//   fontWeight: 600,
//   textAlign: "center",
// };

// const sectionTitle = {
//   fontSize: 18,
//   fontWeight: 700,
//   margin: "1rem 0 0.5rem",
//   color: "#1e293b",
// };

// const list = {
//   listStyleType: "disc",
//   paddingLeft: 20,
//   marginBottom: 16,
// };

// const coverageBox = {
//   backgroundColor: "#fff",
//   border: "1px solid #ddd",
//   padding: "1rem",
//   borderRadius: 10,
//   fontSize: 14,
// };

// export default HomeInsurance;


import React, { useState } from "react";
import axios from "axios";

function HomeInsurance() {
  const [formData, setFormData] = useState({
    policyHolderName: "",
    city: "",
    buildingValue: "",
    contentValue: "",
    carpetArea: "",
    buildingType: "",
    earthquakeCover: false,
    floodCover: false,
  });

  const [responseData, setResponseData] = useState(null);
  const [error, setError] = useState("");
  const [buttonPressed, setButtonPressed] = useState(false);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: type === "checkbox" ? checked : value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/api/homepolicy/calculatePremium",
        formData
      );
      setResponseData(response.data);
      setError("");
    } catch (err) {
      setError("Something went wrong. Please try again later.");
      setResponseData(null);
    }
  };

  return (
    <div style={page}>
      {/* Left - Calculator */}
      <div style={rightPanel}>
        <h1 style={title}>Home Insurance Premium Calculator</h1>

        <form onSubmit={handleSubmit} style={form}>
          <FloatingInput
            label="Policy Holder Name"
            name="policyHolderName"
            value={formData.policyHolderName}
            onChange={handleChange}
            required
          />
          <FloatingInput
            label="City"
            name="city"
            value={formData.city}
            onChange={handleChange}
            required
          />
          <FloatingInput
            label="Building Value (₹)"
            name="buildingValue"
            type="number"
            value={formData.buildingValue}
            onChange={handleChange}
            required
          />
          <FloatingInput
            label="Content Value (₹)"
            name="contentValue"
            type="number"
            value={formData.contentValue}
            onChange={handleChange}
            required
          />
          <FloatingInput
            label="Carpet Area (sq.ft)"
            name="carpetArea"
            type="number"
            value={formData.carpetArea}
            onChange={handleChange}
            required
          />

          <div style={floatingSelectWrapper}>
            <select
              name="buildingType"
              value={formData.buildingType}
              onChange={handleChange}
              required
              style={floatingSelect}
            >
              <option value="" disabled></option>
              <option value="Apartment">Apartment</option>
              <option value="Independent House">Independent House</option>
              <option value="Villa">Villa</option>
              <option value="Other">Other</option>
            </select>
            <label style={floatingSelectLabel}>Building Type</label>
          </div>

          <div style={togglesWrapper}>
            <ToggleSwitch
              label="Earthquake Cover"
              name="earthquakeCover"
              checked={formData.earthquakeCover}
              onChange={handleChange}
            />
            <ToggleSwitch
              label="Flood Cover"
              name="floodCover"
              checked={formData.floodCover}
              onChange={handleChange}
            />
          </div>

          <button
            type="submit"
            style={{ ...button, ...(buttonPressed ? buttonActive : {}) }}
            onMouseDown={() => setButtonPressed(true)}
            onMouseUp={() => setButtonPressed(false)}
            onMouseLeave={() => setButtonPressed(false)}
          >
            Calculate Premium
          </button>
        </form>

        {responseData && (
          <div style={responseBox}>
            <h2>Premium Details</h2>
            <DetailRow label="Policy ID" value={responseData.policyId} />
            <DetailRow label="Holder" value={responseData.policyHolderName} />
            <DetailRow label="Risk" value={responseData.locationRisk} />
            <DetailRow label="Premium" value={`₹${responseData.premium}`} />
            <DetailRow label="Start Date" value={responseData.startDate} />
            <DetailRow label="End Date" value={responseData.endDate} />
          </div>
        )}

        {error && <p style={errorStyle}>{error}</p>}
      </div>

      {/* Right - Info Panel */}
      <div style={leftPanel}>
        <h2 style={sectionTitle}>🏠 Insurance Overview</h2>
        <ul style={list}>
          <li>• Protects your home structure & contents.</li>
          <li>• Optional natural disaster cover.</li>
          <li>• Customizable based on your building type.</li>
        </ul>

        <h3 style={sectionTitle}>📌 Quick Tips</h3>
        <ul style={list}>
          <li>✔️ Estimate correct building & content value.</li>
          <li>✔️ Include flood/earthquake for high-risk areas.</li>
          <li>✔️ Recalculate yearly to stay protected.</li>
        </ul>

        <h3 style={sectionTitle}>📊 Example Coverage</h3>
        <p style={coverageBox}>
          <strong>Premium: ₹5,400/year</strong><br />
          Building: ₹50L<br />
          Contents: ₹10L<br />
          Covers: Fire, Theft, Earthquake
        </p>

        <h3 style={sectionTitle}>❓FAQs</h3>
        <ul style={list}>
          <li>• What’s covered in content value?</li>
          <li>• How is premium calculated?</li>
          <li>• What are the exclusions?</li>
        </ul>

        <h3 style={sectionTitle}>📞 Support</h3>
        <p>Email: support@insurewise.com<br />Phone: +91-9876543210</p>
      </div>
    </div>
  );
}

// COMPONENTS

function FloatingInput({ label, name, type = "text", value, onChange, required }) {
  const [focused, setFocused] = useState(false);
  const shouldFloat = focused || value;

  return (
    <div style={floatingInputWrapper}>
      <input
        style={{
          ...floatingInput,
          borderColor: shouldFloat ? "#2563eb" : "#ccc",
        }}
        type={type}
        name={name}
        value={value}
        onChange={onChange}
        onFocus={() => setFocused(true)}
        onBlur={() => setFocused(false)}
        placeholder=" "
        required={required}
      />
      <label
        style={{
          ...floatingLabel,
          top: shouldFloat ? 4 : "50%",
          fontSize: shouldFloat ? 12 : 16,
          transform: shouldFloat ? "translateY(0)" : "translateY(-50%)",
          color: shouldFloat ? "#2563eb" : "#999",
        }}
      >
        {label}
      </label>
    </div>
  );
}

function ToggleSwitch({ label, name, checked, onChange }) {
  return (
    <label style={toggleLabel}>
      <input
        type="checkbox"
        name={name}
        checked={checked}
        onChange={onChange}
        style={{ display: "none" }}
      />
      <span style={{ ...toggleSlider, ...(checked ? toggleSliderChecked : {}) }}>
        <span
          style={{
            ...knobStyle,
            transform: checked ? "translateX(22px)" : "translateX(0)",
          }}
        />
      </span>
      <span style={toggleText}>{label}</span>
    </label>
  );
}

function DetailRow({ label, value }) {
  return (
    <p style={{ margin: "6px 0", fontSize: 15 }}>
      <strong>{label}:</strong> {value}
    </p>
  );
}

// STYLES

const page = {
  display: "flex",
  padding: "2rem",
  gap: "2rem",
  fontFamily: "'Segoe UI', sans-serif",
};

const leftPanel = {
  flex: 1,
  background: "#f0f4f8",
  padding: "2rem",
  borderRadius: 16,
  boxShadow: "0 4px 12px rgba(0,0,0,0.05)",
};

const rightPanel = {
  flex: 1.3,
  background: "#fff",
  padding: "2rem",
  borderRadius: 16,
  boxShadow: "0 4px 12px rgba(0,0,0,0.1)",
};

const title = {
  fontSize: 24,
  fontWeight: 700,
  color: "#003366",
  marginBottom: 24,
  textAlign: "center",
};

const form = {
  display: "flex",
  flexDirection: "column",
  gap: 20,
};

const floatingInputWrapper = { position: "relative" };
const floatingInput = {
  width: "100%",
  padding: "14px 16px",
  fontSize: 16,
  borderRadius: 10,
  border: "2px solid #ccc",
  outline: "none",
};
const floatingLabel = {
  position: "absolute",
  left: 18,
  top: "50%",
  pointerEvents: "none",
  transition: "all 0.3s ease",
  backgroundColor: "#fff",
  padding: "0 4px",
};

const floatingSelectWrapper = { position: "relative" };
const floatingSelect = {
  width: "100%",
  padding: "14px 16px",
  fontSize: 16,
  borderRadius: 10,
  border: "2px solid #ccc",
  backgroundColor: "#fff",
};
const floatingSelectLabel = {
  position: "absolute",
  top: "-10px",
  left: 16,
  fontSize: 13,
  color: "#777",
  backgroundColor: "#fff",
  padding: "0 6px",
};

const togglesWrapper = { display: "flex", gap: 24 };
const toggleLabel = { display: "flex", alignItems: "center", gap: 12 };
const toggleSlider = {
  width: 48,
  height: 26,
  borderRadius: 13,
  backgroundColor: "#ccc",
  position: "relative",
  transition: "background 0.3s ease",
};
const toggleSliderChecked = { backgroundColor: "#2563eb" };
const knobStyle = {
  position: "absolute",
  top: 2,
  left: 2,
  width: 22,
  height: 22,
  borderRadius: "50%",
  backgroundColor: "#fff",
  transition: "transform 0.3s ease",
};
const toggleText = { fontSize: 15 };

const button = {
  padding: "14px",
  fontSize: 16,
  borderRadius: 10,
  border: "none",
  backgroundColor: "#007bff",
  color: "#fff",
  fontWeight: 600,
  cursor: "pointer",
  transition: "transform 0.1s ease",
};
const buttonActive = { transform: "scale(0.97)" };

const responseBox = {
  marginTop: 24,
  padding: 20,
  backgroundColor: "#e0f2fe",
  borderRadius: 12,
};

const errorStyle = {
  marginTop: 16,
  color: "#d00000",
  fontWeight: 600,
  textAlign: "center",
};

const sectionTitle = {
  fontSize: 18,
  fontWeight: 700,
  margin: "1rem 0 0.5rem",
  color: "#1e293b",
};

const list = {
  listStyleType: "disc",
  paddingLeft: 20,
  marginBottom: 16,
};

const coverageBox = {
  backgroundColor: "#fff",
  border: "1px solid #ddd",
  padding: "1rem",
  borderRadius: 10,
  fontSize: 14,
};

export default HomeInsurance;


