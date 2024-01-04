import React, { useState } from 'react'
import NormalNavBar from '../Components/NormalNavBar'
import { useAuth } from '../auth/components/AuthService'
import axios from 'axios'
import { useEffect } from 'react'
import Cookies from 'js-cookie'
import { toast } from 'react-toastify'
import { Link } from 'react-router-dom'

axios.defaults.withCredentials = true
function ShipmentsPage() {

  const [userShipments, setUserShipments] = useState([]);

  const auth = useAuth()

  const checkIfProfileExists = async () => {
    await axios.get("http://localhost:8080/api/v1/movingexpress/customers?simpleCheck=true", {
      headers: {
        // @ts-ignore
        "X-XSRF-TOKEN": auth.getXsrfToken(),
      }
    }).then(r => {
      //console.log(r.data)
      console.log(Cookies.get('email'))
      getShipments(Cookies.get('email'))
    })
  }

  const getShipments = async (email: any) => {

    await axios.get("http://localhost:8080/api/v1/movingexpress/shipments", {
      params: {
        email: email
      }
    }).then((r: any) => {
      console.log(r.data)
      setUserShipments(r.data)
    })
  }

  useEffect(() => {
    checkIfProfileExists();
  }, [])

  const yellowTextStyle = { color: '#FFD700' };
  const textStyle = { color: '#000000', marginBottom: '20px' };

  const styleAll = {
    color: '#000000',
    fontFamily: '"Bebas Neue", cursive',
    fontSize: '36px',
    fontWeight: 'bold',
  };

  const styleShipments = {
    color: '#FFD700',
    fontFamily: '"Bebas Neue", cursive',
    fontSize: '36px',
    fontWeight: 'bold',
  };

  const containerStyle = {
    display: 'flex' as 'flex',
    flexDirection: 'column' as 'column',
    minHeight: '72.8vh',
  };


  return (
    <div style={containerStyle}>
      <NormalNavBar />
      <div style={{ flex: 1, padding: '20px' }}>
        <h2 id="shipmentsHeader">
          <span id="allText" style={styleAll}>ALL </span>
          <span id="shipmentsText" style={styleShipments}>SHIPMENTS</span>
        </h2>
        <p id="shipmentsDescription" style={textStyle}>
          A shipment request plays a vital role in ensuring the smooth and
          organized movement of goods from the point of origin to the
          destination. It helps both the shipper and the carrier have a clear
          understanding of the logistics involved, reducing the risk of errors,
          delays, and misunderstandings during the transportation process.
        </p>
        <hr />
        {
          userShipments.length !== 0 ? (
            <table id="shipmentsTable" style={{ width: '95%', borderCollapse: 'collapse', marginTop: '20px' }}>
              <thead>
                <tr>
                  <th id="idHeader" style={{ padding: '10px', borderBottom: '1px solid #DDDDDD' }}>ID</th>
                  <th id="nameHeader" style={{ padding: '10px', borderBottom: '1px solid #DDDDDD' }}>Shipment Name</th>
                  <th id="statusHeader" style={{ padding: '10px', borderBottom: '1px solid #DDDDDD' }}>Status</th>
                  <th id="weightHeader" style={{ padding: '10px', borderBottom: '1px solid #DDDDDD' }}>Approximate Weight</th>
                </tr>
              </thead>
              <tbody>
                {userShipments.map((shipment: any, index) => (
                  <tr id={`shipmentRow-${index}`} key={shipment.shipmentId} style={{ background: '#FFFFFF', borderBottom: '1px solid #E0E0E0' }}>
                    <td id={`shipmentId-${index}`} style={{ ...yellowTextStyle, padding: '10px', border: '1px solid #E0E0E0' }}>
                      <Link to={`/ShipmentDetails?shipmentId=${shipment.shipmentId}`}>
                        <div
                          style={{ fontFamily: 'Bebas Neue, cursive' }}
                          className="px-5 py-2 bg-white hover:bg-companyYellow hover:text-[#2D2D2D]"
                        >
                          {shipment.shipmentId}
                        </div>
                      </Link>
                    </td>
                    <td id={`shipmentName-${index}`} style={{ padding: '10px', border: '1px solid #E0E0E0' }}>
                      {shipment.shipmentName}
                    </td>
                    <td id={`status-${index}`} style={{ ...yellowTextStyle, padding: '10px', border: '1px solid #E0E0E0' }}>
                      {shipment.status}
                    </td>
                    <td id={`weight-${index}`} style={{ ...yellowTextStyle, padding: '10px', border: '1px solid #E0E0E0' }}>
                      {shipment.weight.toLocaleString()} lbs
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          ) : (
            <div id="noShipmentsMessage" style={{ textAlign: 'center', opacity: '0.5', fontSize: '18px' }}>
              <h2>
                <span style={styleAll}>Empty - User Has No </span>
                <span style={styleShipments}>SHIPMENTS</span>
              </h2>
            </div>
          )
        }
      </div>
    </div>
  );
}


export default ShipmentsPage;