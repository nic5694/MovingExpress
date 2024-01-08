import React from 'react'
import { Link } from 'react-router-dom'

function ShipmentContainer(props: any) {

  const receivedObject = props.shipment;

  const generateEyeIcon = () => {
    return (

      <svg width="17" height="16" viewBox="0 0 22 16" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path d="M11 5C10.2044 5 9.44129 5.31607 8.87868 5.87868C8.31607 6.44129 8 7.20435 8 8C8 8.79565 8.31607 9.55871 8.87868 10.1213C9.44129 10.6839 10.2044 11 11 11C11.7956 11 12.5587 10.6839 13.1213 10.1213C13.6839 9.55871 14 8.79565 14 8C14 7.20435 13.6839 6.44129 13.1213 5.87868C12.5587 5.31607 11.7956 5 11 5ZM11 13C9.67392 13 8.40215 12.4732 7.46447 11.5355C6.52678 10.5979 6 9.32608 6 8C6 6.67392 6.52678 5.40215 7.46447 4.46447C8.40215 3.52678 9.67392 3 11 3C12.3261 3 13.5979 3.52678 14.5355 4.46447C15.4732 5.40215 16 6.67392 16 8C16 9.32608 15.4732 10.5979 14.5355 11.5355C13.5979 12.4732 12.3261 13 11 13ZM11 0.5C6 0.5 1.73 3.61 0 8C1.73 12.39 6 15.5 11 15.5C16 15.5 20.27 12.39 22 8C20.27 3.61 16 0.5 11 0.5Z" fill="#F8F8F8" />
      </svg>

    );
  }

  return (
    <div className='w-[100%] md:w-[250px] py-5 px-5 rounded-md shadow-md bg-white border'>

      <div className='flex justify-between mb-5'>
        <div style={{ fontFamily: 'Bebas Neue, cursive' }} className='w-[70%] opacity-70'>
          <Link to={`/ShipmentDetails?shipmentId=${receivedObject.shipmentId}`}>
            <div>
              {receivedObject.shipmentName}
            </div>
          </Link>
        </div>

        <div >
          <button style={{ transform: "rotate(90deg)" }}>...</button>
        </div>

      </div>

      <div className="text-sm">
        <div>
          <span className="font-light">Status :</span> <span className="text-companyYellow">{receivedObject.status}</span>
        </div>

        <div>
          <span className="font-light">Weight :</span> <span className="text-companyYellow" >{receivedObject.weight.toLocaleString()} lbs</span>
        </div>
      </div>

      <div className='flex justify-end'>
        <button style={{ alignItems: "center" }} className="bg-companyYellow w-[30px] h-[30px] rounded-full text-white flex justify-center">
          <Link to={`/ShipmentDetails?shipmentId=${receivedObject.shipmentId}`}>
            <div>
              {generateEyeIcon()}
            </div>
          </Link>
        </button>
      </div>

    </div>
  )
}

export default ShipmentContainer



