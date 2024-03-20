// Receipt.js

import React from 'react';
import jsPDF from 'jspdf';
import './Recipt.css'; // Import the CSS file for styling

class Receipt extends React.Component {
    generatePDF = () => {
        const jsPDF = require('jspdf');
        require('jspdf-autotable');
        const pdf = new jsPDF.default();
      
        const { transactionDetails } = this.props;
      
        pdf.setFont('Arial');
        pdf.setFontSize(12);
      
        // Header
        pdf.text(20, 20, 'Vinland Farms Receipt');
        pdf.text(20, 30, 'Thank you for choosing Vinland Farms for your agricultural needs.');
        pdf.text(20, 40, 'Here is your transaction receipt:');
      
        const data = [['Item', 'Details']];
        data.push(['Transaction ID', transactionDetails.transactionId]);
        data.push(['Farmer', transactionDetails.farmerEmail]);
        data.push(['Dealer', transactionDetails.dealerEmail]);
        data.push(['Crop Type', transactionDetails.cropType]);
        data.push(['Quantity', transactionDetails.quantity]);
        data.push(['Price per Kg', `$${transactionDetails.pricePerKg}`]);
        data.push(['Total Price', `$${transactionDetails.totalPrice}`]);
        data.push(['Booking Time', new Date(transactionDetails.bookingTime).toLocaleString()]);
      
        // Table styling
        pdf.autoTable({
          head: data,
          startY: 60,
          theme: 'plain',
          styles: {
            fontSize: 12,
            overflow: 'linebreak',
            columnWidth: 'wrap',
          },
          margin: { top: 60 },
        });
      
        // Footer
        pdf.text(20, pdf.autoTable.previous.finalY + 20, 'Thank you for your business!');
      
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
