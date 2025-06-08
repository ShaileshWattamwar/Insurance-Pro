// import React from "react";
// import { useNavigate } from "react-router-dom";
// import { Home, Car, Heart, ShieldCheck, Info, Smartphone, Stethoscope, Globe } from "lucide-react";

// const cards = [
//   { label: "Home Insurance", icon: <Home size={40} />, route: "/home" },
//   { label: "Auto Insurance", icon: <Car size={40} />, route: "/auto" },
//   { label: "Life Insurance", icon: <Heart size={40} />, route: "/life" },
// ];

// export default function Dashboard() {
//   const navigate = useNavigate();

//   return (
//     <div className="space-y-16">
//       {/* Welcome Section */}
//       <section>
//         <h1 className="text-4xl font-extrabold text-indigo-700 mb-3">Welcome to InsureWise</h1>
//         <p className="text-gray-600 text-lg max-w-3xl">
//           Your smart insurance companion. Instantly calculate and compare premiums for home, auto, and life insurance. No login needed!
//         </p>
//       </section>

//       {/* Product Cards */}
//       <section>
//         <h2 className="text-2xl font-bold text-gray-800 mb-6">Explore Our Insurance Plans</h2>
//         <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
//           {cards.map(({ label, icon, route }) => (
//             <div
//               key={label}
//               onClick={() => navigate(route)}
//               className="cursor-pointer bg-white shadow-lg rounded-xl p-6 flex flex-col items-center text-indigo-700 hover:shadow-2xl transition-shadow"
//             >
//               {icon}
//               <h3 className="mt-3 text-xl font-semibold">{label}</h3>
//               <button
//                 className="mt-5 bg-indigo-600 text-white py-2 px-4 rounded-md hover:bg-indigo-700 font-medium"
//                 onClick={(e) => {
//                   e.stopPropagation();
//                   navigate(route);
//                 }}
//               >
//                 Calculate Premium
//               </button>
//             </div>
//           ))}
//         </div>
//       </section>

//       {/* Why Us Section */}
//       <section className="bg-white p-8 rounded-xl shadow">
//         <h2 className="text-2xl font-bold text-indigo-700 mb-4">Why Choose InsureWise?</h2>
//         <ul className="list-disc pl-6 space-y-2 text-gray-700 text-lg">
//           <li>Real-time premium calculation</li>
//           <li>Compare multiple policy types in one place</li>
//           <li>No login required to explore plans</li>
//           <li>Modern, transparent, and easy-to-use interface</li>
//           <li>Access policy details and benefits instantly</li>
//         </ul>
//       </section>

//       {/* Statistics */}
//       <section className="grid grid-cols-1 md:grid-cols-3 gap-6 text-center">
//         <div className="bg-indigo-50 p-6 rounded-lg shadow">
//           <ShieldCheck size={32} className="mx-auto text-indigo-600 mb-2" />
//           <p className="text-2xl font-bold">12,500+</p>
//           <p className="text-gray-600">Policies Calculated</p>
//         </div>
//         <div className="bg-indigo-50 p-6 rounded-lg shadow">
//           <Info size={32} className="mx-auto text-indigo-600 mb-2" />
//           <p className="text-2xl font-bold">98%</p>
//           <p className="text-gray-600">Customer Satisfaction</p>
//         </div>
//         <div className="bg-indigo-50 p-6 rounded-lg shadow">
//           <Heart size={32} className="mx-auto text-indigo-600 mb-2" />
//           <p className="text-2xl font-bold">3 Policy Types</p>
//           <p className="text-gray-600">All in One Place</p>
//         </div>
//       </section>

//       {/* Mobile App Promotion */}
//       <section className="bg-white p-8 rounded-xl shadow">
//         <h2 className="text-2xl font-bold text-indigo-700 mb-4">InsureWise Mobile App</h2>
//         <p className="text-gray-700 mb-4">
//           Download our mobile app for instant policy generation, health monitoring, and claim tracking — anytime, anywhere.
//         </p>
//         <div className="flex gap-6 items-center">
//           <Smartphone size={36} className="text-indigo-600" />
//           <p className="text-gray-600">Now available on Android & iOS</p>
//         </div>
//       </section>

//       {/* Additional Services */}
//       <section className="grid grid-cols-1 md:grid-cols-3 gap-8">
//         <div className="bg-white p-6 rounded-xl shadow text-center">
//           <Stethoscope size={32} className="mx-auto text-indigo-600 mb-3" />
//           <h3 className="font-bold text-lg mb-2">Health Check Plans</h3>
//           <p className="text-gray-600">Monitor your health with face-scan vitals & wellness insights.</p>
//         </div>
//         <div className="bg-white p-6 rounded-xl shadow text-center">
//           <Globe size={32} className="mx-auto text-indigo-600 mb-3" />
//           <h3 className="font-bold text-lg mb-2">Global Travel Coverage</h3>
//           <p className="text-gray-600">Trip insurance with cancellation & pre-existing disease coverage.</p>
//         </div>
//         <div className="bg-white p-6 rounded-xl shadow text-center">
//           <ShieldCheck size={32} className="mx-auto text-indigo-600 mb-3" />
//           <h3 className="font-bold text-lg mb-2">Instant Policy Issuance</h3>
//           <p className="text-gray-600">Get covered in minutes with our AI-backed approval system.</p>
//         </div>
//       </section>
//     </div>
//   );
// }


import React from "react";
import { useNavigate } from "react-router-dom";
import {
  Home,
  Car,
  Heart,
  ShieldCheck,
  Info,
  Smartphone,
  Stethoscope,
  Globe,
  MessageCircle,
  Newspaper,
  Star,
  HelpCircle
} from "lucide-react";

const cards = [
  { label: "Home Insurance", icon: <Home size={40} />, route: "/home" },
  { label: "Auto Insurance", icon: <Car size={40} />, route: "/auto" },
  { label: "Life Insurance", icon: <Heart size={40} />, route: "/life" },
];

export default function Dashboard() {
  const navigate = useNavigate();

  return (
    <div className="space-y-16">
      {/* Hero Section */}
      <section>
        <h1 className="text-4xl font-extrabold text-indigo-700 mb-3">Welcome to InsureWise</h1>
        <p className="text-gray-600 text-lg max-w-3xl">
          Instantly compare and calculate insurance premiums. No login needed — just clarity and convenience.
        </p>
      </section>

      {/* Insurance Product Cards */}
      <section>
        <h2 className="text-2xl font-bold text-gray-800 mb-6">Our Insurance Plans</h2>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
          {cards.map(({ label, icon, route }) => (
            <div
              key={label}
              onClick={() => navigate(route)}
              className="cursor-pointer bg-white shadow-lg rounded-xl p-6 flex flex-col items-center text-indigo-700 hover:shadow-2xl transition-shadow"
            >
              {icon}
              <h3 className="mt-3 text-xl font-semibold">{label}</h3>
              <button
                className="mt-5 bg-indigo-600 text-white py-2 px-4 rounded-md hover:bg-indigo-700 font-medium"
                onClick={(e) => {
                  e.stopPropagation();
                  navigate(route);
                }}
              >
                Calculate Premium
              </button>
            </div>
          ))}
        </div>
      </section>

      {/* Key Benefits */}
      <section className="bg-white p-8 rounded-xl shadow">
        <h2 className="text-2xl font-bold text-indigo-700 mb-4">Why Choose InsureWise?</h2>
        <ul className="list-disc pl-6 space-y-2 text-gray-700 text-lg">
          <li>Instant premium quotes with no login</li>
          <li>Side-by-side policy comparisons</li>
          <li>Paperless process from quote to policy</li>
          <li>Affordable plans with wide coverage</li>
          <li>Trusted by 12,000+ policyholders</li>
        </ul>
      </section>

      {/* Statistics */}
      <section className="grid grid-cols-1 md:grid-cols-3 gap-6 text-center">
        <div className="bg-indigo-50 p-6 rounded-lg shadow">
          <ShieldCheck size={32} className="mx-auto text-indigo-600 mb-2" />
          <p className="text-2xl font-bold">12,500+</p>
          <p className="text-gray-600">Policies Calculated</p>
        </div>
        <div className="bg-indigo-50 p-6 rounded-lg shadow">
          <Info size={32} className="mx-auto text-indigo-600 mb-2" />
          <p className="text-2xl font-bold">98%</p>
          <p className="text-gray-600">Customer Satisfaction</p>
        </div>
        <div className="bg-indigo-50 p-6 rounded-lg shadow">
          <Heart size={32} className="mx-auto text-indigo-600 mb-2" />
          <p className="text-2xl font-bold">3 Policy Types</p>
          <p className="text-gray-600">All in One Place</p>
        </div>
      </section>

      {/* Mobile App Promo */}
      <section className="bg-white p-8 rounded-xl shadow">
        <h2 className="text-2xl font-bold text-indigo-700 mb-4">Get the InsureWise App</h2>
        <div className="flex gap-6 items-center">
          <Smartphone size={36} className="text-indigo-600" />
          <div>
            <p className="text-gray-700">Policy at your fingertips. Buy, renew, and claim from your mobile.</p>
            <p className="text-sm text-gray-500">Available on Android & iOS</p>
          </div>
        </div>
      </section>

      {/* Additional Services */}
      <section className="grid grid-cols-1 md:grid-cols-3 gap-8">
        <div className="bg-white p-6 rounded-xl shadow text-center">
          <Stethoscope size={32} className="mx-auto text-indigo-600 mb-3" />
          <h3 className="font-bold text-lg mb-2">Health Checkups</h3>
          <p className="text-gray-600">Add-on packages for preventive diagnostics & screenings.</p>
        </div>
        <div className="bg-white p-6 rounded-xl shadow text-center">
          <Globe size={32} className="mx-auto text-indigo-600 mb-3" />
          <h3 className="font-bold text-lg mb-2">Travel Insurance</h3>
          <p className="text-gray-600">Global trip coverage with emergency assistance benefits.</p>
        </div>
        <div className="bg-white p-6 rounded-xl shadow text-center">
          <ShieldCheck size={32} className="mx-auto text-indigo-600 mb-3" />
          <h3 className="font-bold text-lg mb-2">Instant Policy Issuance</h3>
          <p className="text-gray-600">Fully digital, hassle-free policy generation.</p>
        </div>
      </section>

      {/* FAQs */}
      <section className="bg-white p-8 rounded-xl shadow space-y-6">
        <h2 className="text-2xl font-bold text-indigo-700 mb-4">Frequently Asked Questions</h2>
        <div className="space-y-4">
          <div>
            <h3 className="flex items-center gap-2 text-lg font-semibold text-gray-800">
              <HelpCircle size={20} /> Do I need to register to calculate premiums?
            </h3>
            <p className="text-gray-600">No, you can explore and calculate premiums freely without registration.</p>
          </div>
          <div>
            <h3 className="flex items-center gap-2 text-lg font-semibold text-gray-800">
              <HelpCircle size={20} /> Can I compare multiple policy types?
            </h3>
            <p className="text-gray-600">Yes, our dashboard allows quick side-by-side comparison of all available plans.</p>
          </div>
          <div>
            <h3 className="flex items-center gap-2 text-lg font-semibold text-gray-800">
              <HelpCircle size={20} /> How secure is my personal information?
            </h3>
            <p className="text-gray-600">We use enterprise-grade security protocols to ensure your data is always protected.</p>
          </div>
        </div>
      </section>

      {/* Testimonials */}
      <section className="space-y-6">
        <h2 className="text-2xl font-bold text-indigo-700">What Our Users Say</h2>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          {[
            {
              name: "Amit K.",
              feedback: "InsureWise helped me understand my policy better. Instant premium quotes made decision-making easier.",
            },
            {
              name: "Sneha R.",
              feedback: "Love how simple and fast the UI is. Calculating premium doesn’t feel like a chore anymore!",
            },
            {
              name: "Vikram S.",
              feedback: "Highly recommend the platform — smooth and professional from start to finish.",
            },
          ].map((t, i) => (
            <div key={i} className="bg-white p-6 rounded-xl shadow space-y-3">
              <Star className="text-yellow-500" />
              <p className="text-gray-700 italic">“{t.feedback}”</p>
              <p className="text-sm text-gray-500">- {t.name}</p>
            </div>
          ))}
        </div>
      </section>

      {/* News & Updates */}
      <section className="bg-white p-8 rounded-xl shadow space-y-4">
        <h2 className="text-2xl font-bold text-indigo-700">Latest News</h2>
        <div className="space-y-2">
          <div className="flex items-start gap-3">
            <Newspaper size={20} className="text-indigo-600 mt-1" />
            <div>
              <p className="font-semibold text-gray-800">New Term Plan Launched</p>
              <p className="text-sm text-gray-600">Our new term plan now offers disability benefits and income replacement options.</p>
            </div>
          </div>
          <div className="flex items-start gap-3">
            <Newspaper size={20} className="text-indigo-600 mt-1" />
            <div>
              <p className="font-semibold text-gray-800">Health Partner Tie-Up</p>
              <p className="text-sm text-gray-600">Partnered with Fit360 to offer discounts on annual medical checkups.</p>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
}
