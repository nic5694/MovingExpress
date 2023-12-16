import React from 'react'
import NormalNavBar from '../Components/NormalNavBar'
import { useAuth } from '../auth/components/AuthService'
import axios from 'axios'
import { useEffect } from 'react'

axios.defaults.withCredentials = true
function ShipmentsPage() {

  const auth = useAuth()
  
  const checkIfProfileExists = async () => {
    await axios.get("http://localhost:8080/api/v1/movingexpress/customers?simpleCheck=true", {
        headers: {
            // @ts-ignore

            "X-XSRF-TOKEN": auth.getXsrfToken(),
        }
    }).then(r => {console.log(r.data)}) 
  }

  useEffect(() => {
    checkIfProfileExists();
  },[])

  return (
    <div>
      <NormalNavBar />
      Shipments Page
    </div>

  )
}

export default ShipmentsPage;