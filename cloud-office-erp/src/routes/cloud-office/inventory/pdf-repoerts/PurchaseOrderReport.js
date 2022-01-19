import React, { Component } from "react";
import jsPDF from 'jspdf';
import 'jspdf-autotable'

export default class PurchaseOrderReport extends Component{

    constructor(props){
        super(props);

        window.purchase_order_report = this;
        alert('PurchaseOrderReport')
    }
    

    state = {
        
    headers: [
        'Vendor Name',
        'Material Name',
        'Material Quntity',
        'Material Rate',
        'Material Amount',
        'Material Remark',
     ]
    }

    generatePdf = () =>{
       
        var doc = new jsPDF('p','pt');

        doc.setFontSize(15);
        doc.setFont('Arial');
        doc.text("Financial Year", 20, 35);
        doc.text("Hello world!", 280, 35);
        doc.text("Stores Copy", 480, 35);

        doc.setLineWidth(0.5);
        doc.rect(10, 20, 575, 20);

        doc.setLineWidth(0.5);
        doc.rect(10, 20, 575, 805);

        //doc.setFontSize(15);
        doc.text("Book No", 20, 65);

        doc.text("To,", 20, 85);
        doc.setLineWidth(0.5);
        doc.setLineDash([10, 1, 1, 2], 1);
        doc.line(35, 100, 180, 100);
        doc.line(35, 120, 180, 120);

        doc.text("Requisition No:", 230, 65);
        doc.text("087557890", 330, 65);

        doc.text("Name of the person to whom material are to be handed over.", 20, 150);

        doc.text("Please supply the following articles", 220, 180);

        doc.text("Officer in charge", 440, 750);
        doc.text("Health Department", 440, 770);

        doc.setLineDash([10, 0, 0, 0], 0);

        doc.autoTable({
            head: [this.state.headers],
  body: [purchaseOrderList],
  startY: 200,
  theme: 'grid'   
});

  window.open(doc.output('bloburl'), '_blank');

        //doc.save('a.pdf');
    }

    render(){
        return(
            <div>
                <h1>PDF</h1>

                <button onClick={this.generatePdf}>Generate PDF</button>
            </div>
        );
    }
}