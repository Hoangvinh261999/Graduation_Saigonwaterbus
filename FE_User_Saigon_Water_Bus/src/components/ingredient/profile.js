import React, { useState, useEffect } from "react";
import axios from "axios";
import Popup from "../Popup";
import { useTranslation } from 'react-i18next';
const apiUrl = process.env.REACT_APP_API_URL;

const UserDetailsPage = () => {
  const { t } = useTranslation();
  const [userDetail, setUserDetail] = useState(null);
  const [isEditPopupOpen, setIsEditPopupOpen] = useState(false);
  const [editData, setEditData] = useState({});
  const [popupMessage, setPopupMessage] = useState("");

  useEffect(() => {
    const fetchUserDetail = async () => {
      try {
        const token = localStorage.getItem("token");
        const headers = { Authorization: `Bearer ${token}` };
        const response = await axios.get(
            `${apiUrl}/profile`,
            { headers }
        );
        setUserDetail(response.data.result);
        console.log(response.data.result)
      } catch (error) {
        console.error("Error fetching user detail:", error);
      }
    };

    fetchUserDetail();
  }, []);

  const handleEditClick = () => {
    setEditData({ ...userDetail });
    setIsEditPopupOpen(true);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setEditData({ ...editData, [name]: value });
  };

  const handleUpdate = async () => {
    try {
      const token = localStorage.getItem("token");
      const headers = { Authorization: `Bearer ${token}` };
      await axios.put(
          `${apiUrl}/profile`,
          editData,
          { headers }
      );
      setUserDetail(editData);
      setIsEditPopupOpen(false);
      setPopupMessage(t('profile.updateSuccess'));
    } catch (error) {
      console.error("Error updating user detail:", error);
      setPopupMessage(t('profile.updateFailure'));
    }
  };

  const updateProfileName = async (e) => {
    e.preventDefault();
    try {
      const token = localStorage.getItem("token");
      const headers = { Authorization: `Bearer ${token}` };
      const updateData = {
        firstname: editData.firstname,
        lastname: editData.lastname,
      };
      const response = await axios.post(
          `${apiUrl}/profile/update`,
          updateData,
          { headers }
      );
      if (response.status === 200) {
        setUserDetail((prevDetail) => ({
          ...prevDetail,
          firstname: updateData.firstname,
          lastname: updateData.lastname,
        }));
        setIsEditPopupOpen(false);
        setPopupMessage(t('profile.updateSuccess'));
      }
    } catch (error) {
      console.error("Error updating profile name:", error);
      setPopupMessage(t('profile.updateFailure'));
    }
  };

  return (
      <div className="container mx-auto px-4">
       <div className="mt-8">
  <h1 className="text-3xl md:text-4xl font-bold text-black mb-8">{t('profile.userDetails')}</h1>
  {userDetail && (
    <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
      <div className="bg-gradient-to-br shadow-md rounded-lg p-4 md:p-6">
        <div className="mb-6">
          <label className="block text-xs md:text-sm font-semibold text-gray-800 mb-2">
            {t('profile.firstName')}:
          </label>
          <p className="text-base md:text-lg flex text-black font-bold items-center">
            {userDetail.firstname}
            <svg
              viewBox="0 0 1024 1024"
              fill="currentColor"
              height="1em"
              width="1em"
              className="mx-4 cursor-pointer"
              onClick={handleEditClick}
            >
              <path d="M257.7 752c2 0 4-.2 6-.5L431.9 722c2-.4 3.9-1.3 5.3-2.8l423.9-423.9a9.96 9.96 0 000-14.1L694.9 114.9c-1.9-1.9-4.4-2.9-7.1-2.9s-5.2 1-7.1 2.9L256.8 538.8c-1.5 1.5-2.4 3.3-2.8 5.3l-29.5 168.2a33.5 33.5 0 009.4 29.8c6.6 6.4 14.9 9.9 23.8 9.9zm67.4-174.4L687.8 215l73.3 73.3-362.7 362.6-88.9 15.7 15.6-89zM880 836H144c-17.7 0-32 14.3-32 32v36c0 4.4 3.6 8 8 8h784c4.4 0 8-3.6 8-8v-36c0-17.7-14.3-32-32-32z"/>
            </svg>
          </p>
        </div>
        <div className="mb-6">
          <label className="block text-xs md:text-sm font-semibold text-gray-800 mb-2">
            {t('profile.lastName')}:
          </label>
          <p className="text-base md:text-lg flex text-black font-bold items-center">
            {userDetail.lastname}
            <svg
              viewBox="0 0 1024 1024"
              fill="currentColor"
              height="1em"
              width="1em"
              className="mx-4 cursor-pointer"
              onClick={handleEditClick}
            >
              <path d="M257.7 752c2 0 4-.2 6-.5L431.9 722c2-.4 3.9-1.3 5.3-2.8l423.9-423.9a9.96 9.96 0 000-14.1L694.9 114.9c-1.9-1.9-4.4-2.9-7.1-2.9s-5.2 1-7.1 2.9L256.8 538.8c-1.5 1.5-2.4 3.3-2.8 5.3l-29.5 168.2a33.5 33.5 0 009.4 29.8c6.6 6.4 14.9 9.9 23.8 9.9zm67.4-174.4L687.8 215l73.3 73.3-362.7 362.6-88.9 15.7 15.6-89zM880 836H144c-17.7 0-32 14.3-32 32v36c0 4.4 3.6 8 8 8h784c4.4 0 8-3.6 8-8v-36c0-17.7-14.3-32-32-32z"/>
            </svg>
          </p>
        </div>
        <div className="mb-6">
          <label className="block text-xs md:text-sm font-semibold text-gray-800 mb-2">
            {t('profile.email')}:
          </label>
          <p className="text-base md:text-lg text-black font-bold">{userDetail.email}</p>
        </div>
      </div>
      <div className="bg-gradient-to-br shadow-md rounded-lg p-4 md:p-6">
        <div className="mb-6">
          <label className="block text-xs md:text-sm font-semibold text-gray-800 mb-2">
            {t('profile.phoneNumber')}:
          </label>
          <p className="text-base md:text-lg text-black font-bold">{userDetail.phoneNumber}</p>
        </div>
        <div className="mb-6">
          <label className="block text-xs md:text-sm font-semibold text-gray-800 mb-2">
            {t('profile.username')}:
          </label>
          <p className="text-base md:text-lg text-black font-bold">{userDetail.username}</p>
        </div>
        <div className="mb-6">
          <label className="block text-xs md:text-sm font-semibold text-gray-800 mb-2">
            {t('profile.createdAt')}:
          </label>
          <p className="text-base md:text-lg text-black font-bold">{userDetail.createAt}</p>
        </div>
      </div>
    </div>
  )}
</div>


        {isEditPopupOpen && (
            <div className="fixed inset-0 bg-gray-600 bg-opacity-50 flex items-center justify-center">
              <form onSubmit={updateProfileName}>
                <div className="bg-white rounded-lg shadow-lg p-8 w-96">
                  <h2 className="text-2xl font-bold mb-4">{t('profile.editProfile')}</h2>
                  <div className="mb-4">
                    <label className="block text-sm font-semibold text-gray-800 mb-2">
                      {t('profile.firstName')}:
                    </label>
                    <input
                        type="text"
                        name="firstname"
                        value={editData.firstname}
                        onChange={handleInputChange}
                        className="w-full px-4 py-2 border rounded-lg"
                        required
                    />
                  </div>
                  <div className="mb-4">
                    <label className="block text-sm font-semibold text-gray-800 mb-2">
                      {t('profile.lastName')}:
                    </label>
                    <input
                        type="text"
                        name="lastname"
                        value={editData.lastname}
                        onChange={handleInputChange}
                        className="w-full px-4 py-2 border rounded-lg"
                        required
                    />
                  </div>
                  <div className="mb-4">
                    <label className="block text-sm font-semibold text-gray-800 mb-2">
                      {t('profile.email')}:
                    </label>
                    <input
                        type="email"
                        name="email"
                        value={editData.email}
                        onChange={handleInputChange}
                        className="w-full px-4 py-2 border rounded-lg"
                        disabled
                    />
                  </div>
                  <div className="mb-4">
                    <label className="block text-sm font-semibold text-gray-800 mb-2">
                      {t('profile.phoneNumber')}:
                    </label>
                    <input
                        type="text"
                        name="phoneNumber"
                        value={editData.phoneNumber}
                        onChange={handleInputChange}
                        className="w-full px-4 py-2 border rounded-lg"
                        disabled
                    />
                  </div>
                  <div className="mb-4">
                    <label className="block text-sm font-semibold text-gray-800 mb-2">
                      {t('profile.username')}:
                    </label>
                    <input
                        type="text"
                        name="username"
                        value={editData.username}
                        onChange={handleInputChange}
                        className="w-full px-4 py-2 border rounded-lg"
                        disabled
                    />
                  </div>
                  <div className="mb-4">
                    <label className="block text-sm font-semibold text-gray-800 mb-2">
                      {t('profile.createdAt')}:
                    </label>
                    <input
                        type="date"
                        name="createAt"
                        value={editData.createAt}
                        onChange={handleInputChange}
                        className="w-full px-4 py-2 border rounded-lg"
                        disabled
                    />
                  </div>
                  <div className="flex justify-end">
                    <button
                        onClick={() => setIsEditPopupOpen(false)}
                        className="px-4 py-2 bg-gray-300 rounded-lg mr-4"
                    >
                      {t('profile.cancel')}
                    </button>
                    <button
                        type="submit"
                        className="px-4 py-2 bg-blue-600 text-white rounded-lg"
                    >
                      {t('profile.update')}
                    </button>
                  </div>
                </div>
              </form>
            </div>
        )}

        <Popup message={popupMessage} />
      </div>
  );
};

export default UserDetailsPage;
