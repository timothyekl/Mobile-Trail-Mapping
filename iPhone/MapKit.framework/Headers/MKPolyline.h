//
//  MKPolyline.h
//  MapKit
//
//  Copyright 2010 Apple, Inc. All rights reserved.
//

#if __IPHONE_4_0 <= __IPHONE_OS_VERSION_MAX_ALLOWED

#import <MapKit/MKMultiPoint.h>
#import <MapKit/MKOverlay.h>

@interface MKPolyline : MKMultiPoint <MKOverlay>

+ (MKPolyline *)polylineWithPoints:(MKMapPoint *)points count:(NSUInteger)count;
+ (MKPolyline *)polylineWithCoordinates:(CLLocationCoordinate2D *)coords count:(NSUInteger)count;

@end

#endif
