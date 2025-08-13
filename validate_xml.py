#!/usr/bin/env python3
"""
XML validation script for GitHub Actions
Validates backup_rules.xml and data_extraction_rules.xml
"""
import xml.etree.ElementTree as ET
import sys
import os

def validate_xml_file(filepath):
    """Validate a single XML file"""
    try:
        ET.parse(filepath)
        print(f"✅ {os.path.basename(filepath)} is valid XML")
        return True
    except Exception as e:
        print(f"❌ {os.path.basename(filepath)} error: {e}")
        return False

def main():
    """Main validation function"""
    files_to_validate = [
        'app/src/main/res/xml/backup_rules.xml',
        'app/src/main/res/xml/data_extraction_rules.xml'
    ]
    
    all_valid = True
    for filepath in files_to_validate:
        if not os.path.exists(filepath):
            print(f"❌ File not found: {filepath}")
            all_valid = False
        else:
            if not validate_xml_file(filepath):
                all_valid = False
    
    if all_valid:
        print("✅ All XML files validation passed!")
        sys.exit(0)
    else:
        print("❌ XML validation failed!")
        sys.exit(1)

if __name__ == "__main__":
    main()