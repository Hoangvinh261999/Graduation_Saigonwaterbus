import { useState, useEffect } from 'react';

const usePopup = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [message1, setMessage1] = useState('');
  const [type, setType] = useState('success'); 

  const showPopup = (message1, type = 'success') => {
    setMessage1(message1);
    setType(type);
    setIsOpen(true);
  };

  const closePopup = () => {
    setIsOpen(false);
  };

  useEffect(() => {
    if (isOpen) {
      const timer = setTimeout(() => {
        closePopup();
      }, 4000);

      return () => clearTimeout(timer);
    }
  }, [isOpen]);

  return { isOpen, message1, type, showPopup, closePopup };
};

export default usePopup;
