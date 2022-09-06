import React from 'react';
import icon from '../../assets/img/notification-icon.svg';
import './style.css';

const NotificationButton = () => {
  return (
    <button className='dsmeta-red-btn'>
        <img src={icon} alt='Notificar'/>
    </button>
  )
}

export default NotificationButton;