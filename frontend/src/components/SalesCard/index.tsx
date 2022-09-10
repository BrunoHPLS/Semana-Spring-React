import React, { useEffect, useState } from "react";
import NotificationButton from "../NotificationButton";
import "./style.css";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import axios from "axios";
import { BASE_URL } from "../../utils/request";
import { Sale } from "../../types/sale";

const SalesCard = () => {

    const [minDate,setMinDate] = useState(new Date(new Date().setDate(new Date().getDate() - 365)));
    const [maxDate,setMaxDate] = useState(new Date());

    const [sales,setSales] = useState<Sale[]>([]);

    useEffect(
      ()=>{
        axios.get(
          `${BASE_URL}/sales`, 
          {
            params: {
              size: 5,
              minDate: minDate.toISOString().slice(0,10),
              maxDate: maxDate.toISOString().slice(0,10)
            }
          })
        .then(response => {
          setSales(response.data.content);
        });
      },
      [minDate,maxDate]);

  return (
    <div className="dsmeta-card">
      <h2 className="dsmeta-sales-title">Vendas</h2>
      <div>
        <div className="dsmeta-form-control-container">
          <DatePicker
            selected={minDate}
            onChange={(date: Date) => {setMinDate(date)}}
            className="dsmeta-form-control"
            dateFormat="dd/MM/yyyy"
          />
        </div>
        <div className="dsmeta-form-control-container">
          <DatePicker
            selected={maxDate}
            onChange={(date: Date) => {setMaxDate(date)}}
            className="dsmeta-form-control"
            dateFormat="dd/MM/yyyy"
          />
        </div>
      </div>

      <div>
        <table className="dsmeta-sales-table">
          <thead>
            <tr>
              <th className="show992">ID</th>
              <th className="show576">Data</th>
              <th>Vendedor</th>
              <th className="show992">Visitas</th>
              <th className="show992">Vendas</th>
              <th>Total</th>
              <th>Notificar</th>
            </tr>
          </thead>
          <tbody>
            {
              sales.map(({id,date,sellerName,visited,deals,amount})=>(
                <tr key={id}>
                  <td className="show992">#{id}</td>
                  <td className="show576">{new Date(date).toLocaleDateString()}</td>
                  <td>{sellerName}</td>
                  <td className="show992">{visited}</td>
                  <td className="show992">{deals}</td>
                  <td>R$ {amount.toFixed(2)}</td>
                  <td>
                    <div className="dsmeta-red-btn-container">
                      <NotificationButton saleId={id} />
                    </div>
                  </td>
                </tr>
              ))
            }
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default SalesCard;
