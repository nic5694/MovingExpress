import React, { useState } from 'react'
import NormalNavBar from '../Components/NormalNavBar'
import { useAuth } from '../auth/components/AuthService'
import axios from 'axios'
import { useEffect } from 'react'
import Cookies from 'js-cookie'
import ShipmentContainer from '../Components/ShipmentContainer'
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




  return (
    <div>
      <NormalNavBar />

      <div className='px-[5%] py-20'>
        <div className="flex flex-col gap-3 pb-7">
          <div
            style={{ fontFamily: 'Bebas Neue, cursive' }}
            className="text-3xl"
          >
            All{' '}
            <span className="text-companyYellow">Shipments</span>
          </div>

          <div className="pb-5 font-light text-sm opacity-90 lg:pr-[10%]">
            Welcome to the Shipments Page! Here, you can effortlessly manage and monitor all your created and ongoing shipments. Whether you're tracking the progress of a shipment or need detailed information about your shipments, this page provides a centralized view of your logistics activities. Explore the list of your shipments, each accompanied by essential details such as shipment name, status and weight. To delve deeper into a specific shipment, simply click on the "Eye Icon" button.
          </div>

          <hr className="border-1 border-companyYellow" />
        </div>

        <div>

          {userShipments.length == 0
            ? (<div className='text-center opacity-25 font-light'>[ No Shipments ]</div>)
            : (

              <div className='flex flex-wrap gap-5'>

                {userShipments.map((shipment: any) => (<ShipmentContainer shipment={shipment} />))}

              </div>


            )}
        </div>

      </div>

    </div>
  );
}


export default ShipmentsPage;