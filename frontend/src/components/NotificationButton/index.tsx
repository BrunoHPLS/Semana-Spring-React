import axios from 'axios';
import React from 'react';
import { toast } from 'react-toastify';
import icon from '../../assets/img/notification-icon.svg';
import { BASE_URL } from '../../utils/request';
import './style.css';

type Props = {
  saleId: number
}

const NotificationButton = ({saleId}:Props) => {

  const sendNotification = () => {
    axios.get(`${BASE_URL}/sales/${saleId}/notification`)
    .then(response=>{
      toast.success("SMS enviado para o id #"+saleId);
    });
  }

  return (
    <button className='dsmeta-red-btn' onClick={sendNotification}>
        <img src={icon} alt='Notificar'/>
    </button>
  )
}

export default NotificationButton;