// Receipt.js

import React from 'react';
import jsPDF from 'jspdf';
import './Recipt.css'; // Import the CSS file for styling

class Receipt extends React.Component {
  generatePDF = () => {
    const pdf = new jsPDF();
    const { transactionDetails } = this.props;

    pdf.text(20, 20, 'Receipt');
    pdf.text(20, 30, '------------------------');
    pdf.text(20, 40, `Transaction ID: ${transactionDetails.transactionId}`);
    pdf.text(20, 50, `Farmer: ${transactionDetails.farmerEmail}`);
    pdf.text(20, 60, `Dealer: ${transactionDetails.dealerEmail}`);
    pdf.text(20, 70, `Crop Type: ${transactionDetails.cropType}`);
    pdf.text(20, 80, `Quantity: ${transactionDetails.quantity}`);
    pdf.text(20, 90, `Price per Kg: $${transactionDetails.pricePerKg}`);
    pdf.text(20, 100, `Total Price: $${transactionDetails.totalPrice}`);
    pdf.text(20, 110, `Booking Time: ${new Date(transactionDetails.bookingTime).toLocaleString()}`);

    pdf.save('receipt.pdf');
  };

  render() {
    const { transactionDetails, onClose } = this.props;

    return (
      <div className="receipt-container">
        <h2 className="receipt-title">Receipt</h2>
        <div className="receipt-info">
          <p>Transaction ID: {transactionDetails.transactionId}</p>
          <p>Farmer: {transactionDetails.farmerEmail}</p>
          <p>Dealer: {transactionDetails.dealerEmail}</p>
          <p>Crop Type: {transactionDetails.cropType}</p>
          <p>Quantity: {transactionDetails.quantity}</p>
          <p>Price per Kg: ${transactionDetails.pricePerKg}</p>
          <p>Total Price: ${transactionDetails.totalPrice}</p>
          <p>Booking Time: {new Date(transactionDetails.bookingTime).toLocaleString()}</p>
        </div>
        <div className="buttons-container">
          <button className="download-button" onClick={this.generatePDF}>
            Download 
          </button>
          <button className="close-button" onClick={onClose}>
            Close
          </button>
        </div>
      </div>
    );
  }
}

export default Receipt;
